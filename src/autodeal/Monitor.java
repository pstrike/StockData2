package autodeal;

import java.util.ArrayList;

import model.Fund;
import model.FundROI;
import dao.FundDBDAO;
import dao.FundROIDBDAO;

public class Monitor {
	private Broker broker;
 
	public Monitor()
	{
		broker = new Broker();
	}
	
	public void buyMonitor()
	{
		// init
		FundDBDAO fundDbDao = new FundDBDAO();
		FundROIDBDAO fundRoiDbDao = new FundROIDBDAO();
		ArrayList<Fund> fundList = null;
	    ArrayList<FundROI> fundRoiList = null;
	    ArrayList <DealBuy> deals = new ArrayList <DealBuy>();
	    ArrayList <DealBuy> orders = new ArrayList <DealBuy>();
	    DealBuy deal = null;
	    orders = broker.getBuyOrder();
	    
	    // process
	    fundList = fundDbDao.getLastestFundList();
		fundRoiList = fundRoiDbDao.getFundROIList();
		
		for(Fund fund : fundList)
		{
			boolean isUptrend = false;
			
			if(fund.getFundRate() > fund.getFundRateYesterday())
			{
				isUptrend = true;
			}
			
			for(FundROI fundRoi : fundRoiList)
			{
				if(fundRoi.getRateLower() <= fund.getFundRate() && fund.getFundRate() < fundRoi.getRateUpper() && ((fundRoi.isUptrend() && isUptrend)||(!fundRoi.isUptrend() && !isUptrend)))
				{
					if(fundRoi.getType().equals("A"))
					{
						fund.setFundHistoricalDealATxnNo(fundRoi.getDealTxnNo());
						fund.setFundHistoricalDealAWinPct(fundRoi.getWinPct());
						fund.setFundHistoricalDealAWinRoi(fundRoi.getRoi());
					}
					else if (fundRoi.getType().equals("B"))
					{
						fund.setFundHistoricalDealBTxnNo(fundRoi.getDealTxnNo());
						fund.setFundHistoricalDealBWinPct(fundRoi.getWinPct());
						fund.setFundHistoricalDealBWinRoi(fundRoi.getRoi());
					}
				}
			}
			
			if(fund.getFundHistoricalDealBWinPct() >= 0.75 
					&& fund.getFundHistoricalDealBWinRoi() >= 0.02 
					&& fund.getFundHistoricalDealBTxnNo() >= 20 
					&& fund.getFundPriceVol()>=200000)
			{
				deal = new DealBuy();
				deal.setId(fund.getFundId());
				deal.setPrice(fund.getFundPrice());
				deal.setVolume(1000);
				
				boolean isBuy = false;
				for(DealBuy buyOrder : orders)
				{
					if(buyOrder.getId().equals(deal.getId()))
					{
						isBuy = true;
					}
				}
				
				if(!isBuy)
				{
					deals.add(deal);
				}
			}
		}
		
		broker.buy(deals);
	}
	
	public void sellMonitor()
	{
		ArrayList <Holding> holdings = broker.getBalance();
		
		ArrayList <DealSell> deals = new ArrayList <DealSell>();
		DealSell deal = null;
		FundDBDAO fundDbDao = new FundDBDAO();
		ArrayList<Fund> fundList = null;
		fundList = fundDbDao.getLastestFundList();
		
		for(Holding holding : holdings)
		{
			if(holding.getId().substring(0, 1).equals("16"))
			{
				for(Fund fund : fundList)
				{
					if(fund.getFundId().equals(holding.getId()))
					{
						if(fund.getFundPrice() / holding.getPriceCost()>=1.02)
						{
							deal = new DealSell();
							deal.setId(holding.getId());
							deal.setPrice(holding.getPrice());
							deal.setVolume(holding.getVolumeAvailable());
							
							deals.add(deal);
						}
					}
				}
			}
		}
		
		broker.sell(deals);
	}
}
