package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Fund;
import utility.log.LoggerManager;
import dao.FundDBDAO;

public class FullFundTRateCalculationController extends Controller {

	@Override
	public void action()
	{
		processFundROI();
	}
	
	private void processFundRateAndAccurateRate()
	{
		// initialization
		FundDBDAO fundDbDao = new FundDBDAO();
		ArrayList <Fund> fundList = null;
		int counter = 0;
		
		try {
			fundList = fundDbDao.getAllFundList();
			
			for(Fund fund : fundList)
			{	
				// handle rate calculation
				if(fund.getFundPrice() > 0 && fund.getFundValueToday()>0)
				{
					fund.setFundRate((fund.getFundPrice()-fund.getFundValueToday())/fund.getFundPrice());
				}
				else if(fund.getFundPrice() > 0 && fund.getFundEstimatedValue()>0)
				{
					fund.setFundRate((fund.getFundPrice()-fund.getFundEstimatedValue())/fund.getFundPrice());
				}
				else
				{
					fund.setFundRate(0);
				}
				
				// handle accurate rate
				if(fund.getFundValueToday()>0)
				{
					fund.setFundAccurateRate((fund.getFundEstimatedValue() - fund.getFundValueToday())/fund.getFundValueToday());
				}
				else
				{
					fund.setFundAccurateRate(0);
				}
				
				fundDbDao.updateFund(fund);
				
				counter++;
				
				System.out.println("Processed: "+counter+"; Total: "+fundList.size());
			}
			
		} catch (Exception e) {
			LoggerManager.getInstance().getLogger().error("Regular Fund Data ETL Exception");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
	}
	
	private void processFundROI()
	{
		// initialization
		FundDBDAO fundDbDao = new FundDBDAO();
		Fund fundId = null;
		Fund fundD1 = null;
		Fund fundD2 = null;
		
		ArrayList <Fund> fundIdList = null;
		ArrayList <Fund> fundList = null;
		
		try {
			fundIdList = fundDbDao.getFundIdList();
			
			while(fundIdList.size() > 0 )
			{
				fundId = fundIdList.get(0);
				fundList = fundDbDao.getFinalizeFundListDescBaseOnFundId(fundId.getFundId());
				
				fundD1 = null;
				fundD2 = null;
				
				for(Fund fund : fundList)
				{
					if(fund.getFundPriceVol()>0)
					{
						fund.setFundPriceD0Avg(fund.getFundPriceAmt() / fund.getFundPriceVol());
					}
					
					if(fundD1!=null)
					{
						fund.setFundValueTmr(fundD1.getFundValueToday());
						fund.setFundPriceD1Avg(fundD1.getFundPriceD0Avg());
					}
					
					if(fundD2!=null)
					{
						fund.setFundPriceD2Avg(fundD2.getFundPriceD0Avg());
					}
					
					if(fund.getFundValueTmr()>0 && fund.getFundPriceD0Avg()>0)
					{
						fund.setFundDealBRoi((fund.getFundValueTmr()-fund.getFundPriceD0Avg())/fund.getFundPriceD0Avg());
					}
					else
					{
						fund.setFundDealBRoi(0);
					}
					
					if(fund.getFundPriceD2Avg()>0 && fund.getFundValueToday()>0)
					{
						fund.setFundDealARoi((fund.getFundPriceD2Avg()-fund.getFundValueToday())/fund.getFundValueToday());
					}
					else
					{
						fund.setFundDealARoi(0);
					}
					
					fundDbDao.updateFund(fund);
					
					fundD2 = fundD1;
					fundD1 = fund;
				}
				
				fundIdList.remove(0);
				
				//System.out.println(fund.getFundId()+" value: "+fund.getFundValue() + " estimated value: "+fund.getFundEstimatedValue() + " price: "+ fund.getFundPrice());
				LoggerManager.getInstance().getLogger().info("Full Fund T - complete:"+fundId.getFundId()+"; pending: "+ fundIdList.size());
			}
			
		} catch (Exception e) {
			LoggerManager.getInstance().getLogger().error("Full Fund T Exception");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
	}
}