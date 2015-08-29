package dto.calculator;

import java.util.ArrayList;

import dao.FundROIDBDAO;
import model.Fund;
import model.FundROI;


public class BuyFundCalculator {
	public boolean isBuy(Fund fund)
	{
		boolean result = false;
		
		FundROIDBDAO fundRoiDbDao = new FundROIDBDAO();
		ArrayList<FundROI> fundRoiList = fundRoiDbDao.getFundROIList();
		
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
		
		return result;
	}
	
	
}
