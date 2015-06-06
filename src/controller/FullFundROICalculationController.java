package controller;

import java.util.ArrayList;

import model.Fund;
import model.FundROI;
import utility.log.LoggerManager;
import dao.FundDBDAO;
import dao.FundROIDBDAO;

public class FullFundROICalculationController extends Controller {

	@Override
	public void action()
	{
		// initialization
		FundDBDAO fundDbDao = new FundDBDAO();
		FundROIDBDAO fundRoiDbDao = new FundROIDBDAO();
		Fund fundId = null;
		Fund lastFund = null;
		
		ArrayList <Fund> fundIdList = null;
		ArrayList <Fund> fundList = null;
		ArrayList<FundROI> roiList = initFundROIList();
		
		try {
			fundRoiDbDao.removeAllFundROI();
			
			fundIdList = fundDbDao.getFundIdList();
			
			while(fundIdList.size() > 0 )
			{
				fundId = fundIdList.get(0);
				fundList = fundDbDao.getFinalizeFundListAscBaseOnFundId(fundId.getFundId());
				
				for(Fund fund : fundList)
				{
					boolean isUptrend = false;
					
					if(lastFund!=null && fund.getFundRate() > lastFund.getFundRate())
					{
						isUptrend  = true;
					}
					
					for(FundROI roi : roiList)
					{
						if(fund.getFundRate() < roi.getRateUpper() && fund.getFundRate() > roi.getRateLower())
						{
							if(roi.getType().equals("A") && isUptrend == roi.isUptrend())
							{
								if(fund.getFundDealARoi()>0)
								{
									roi.setWinTxnNo(roi.getWinTxnNo()+1);
									roi.setRoi(roi.getRoi()+fund.getFundDealARoi());
								}
								roi.setDealTxnNo(roi.getDealTxnNo()+1);
							}
							
							if(roi.getType().equals("B") && isUptrend == roi.isUptrend())
							{
								if(fund.getFundDealBRoi()>0)
								{
									roi.setWinTxnNo(roi.getWinTxnNo()+1);
									roi.setRoi(roi.getRoi()+fund.getFundDealBRoi());
								}
								roi.setDealTxnNo(roi.getDealTxnNo()+1);
							}
						}
					}
					
					lastFund = fund;
				}
				
				fundIdList.remove(0);
				
				//System.out.println(fund.getFundId()+" value: "+fund.getFundValue() + " estimated value: "+fund.getFundEstimatedValue() + " price: "+ fund.getFundPrice());
				LoggerManager.getInstance().getLogger().info("Full Fund ROI Calculation - complete:"+fundId.getFundId()+"; pending: "+ fundIdList.size());
			}
			
			int totalFundNo = 0;
			for(FundROI roi : roiList)
			{
				totalFundNo+=roi.getDealTxnNo();
			}
			
			for(FundROI roi : roiList)
			{
				roi.setTotalTxnNo(totalFundNo);
				
				if(roi.getWinTxnNo()>0)
				{
					roi.setRoi(roi.getRoi()/roi.getWinTxnNo());
				}
				
				fundRoiDbDao.insertFundROI(roi);

				LoggerManager.getInstance().getLogger().info(roi.getName()+": Win No:"+roi.getWinTxnNo()+","+100*roi.getWinPct()+"%; Deal Pct:"+roi.getDealPct()*100+"%; Expectation: "+roi.getExpectation());
			}
			
		} catch (Exception e) {
			LoggerManager.getInstance().getLogger().error("Full Fund ROI Calculation Exception");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
	}
	
	private ArrayList<FundROI> initFundROIList()
	{
		ArrayList<FundROI> roiList = new ArrayList<FundROI>();
		
		/*
		roiList.add(new FundROI("[A]0% - 1%",0.01,0,"A"));
		roiList.add(new FundROI("[A]1% - 2%",0.02,0.01,"A"));
		roiList.add(new FundROI("[A]2% - 3%",0.03,0.02,"A"));
		roiList.add(new FundROI("[A]3% - 4%",0.04,0.03,"A"));
		roiList.add(new FundROI("[A]4% - 5%",0.05,0.04,"A"));
		roiList.add(new FundROI("[A]5% - 6%",0.06,0.05,"A"));
		roiList.add(new FundROI("[A]6% - 7%",0.07,0.06,"A"));
		roiList.add(new FundROI("[A]7% - 8%",0.08,0.07,"A"));
		roiList.add(new FundROI("[A]8% - 9%",0.09,0.08,"A"));
		roiList.add(new FundROI("[A]9% - 10%",0.1,0.09,"A"));
		roiList.add(new FundROI("[A]10% +",1,0.1,"A"));
		
		roiList.add(new FundROI("[A]0% - -1%",0,-0.01,"A"));
		roiList.add(new FundROI("[A]-1% - -2%",-0.01,-0.02,"A"));
		roiList.add(new FundROI("[A]-2% - -3%",-0.02,-0.03,"A"));
		roiList.add(new FundROI("[A]-3% - -4%",-0.03,-0.04,"A"));
		roiList.add(new FundROI("[A]-4% - -5%",-0.04,-0.05,"A"));
		roiList.add(new FundROI("[A]-5% - -6%",-0.05,-0.06,"A"));
		roiList.add(new FundROI("[A]-6% - -7%",-0.06,-0.07,"A"));
		roiList.add(new FundROI("[A]-7% - -8%",-0.07,-0.08,"A"));
		roiList.add(new FundROI("[A]-8% - -9%",-0.08,-0.09,"A"));
		roiList.add(new FundROI("[A]-9% - -10%",-0.09,-0.1,"A"));
		roiList.add(new FundROI("[A]-10% +",-0.1,-1,"A"));
		
		roiList.add(new FundROI("[B]0% - 1%",0.01,0,"B"));
		roiList.add(new FundROI("[B]1% - 2%",0.02,0.01,"B"));
		roiList.add(new FundROI("[B]2% - 3%",0.03,0.02,"B"));
		roiList.add(new FundROI("[B]3% - 4%",0.04,0.03,"B"));
		roiList.add(new FundROI("[B]4% - 5%",0.05,0.04,"B"));
		roiList.add(new FundROI("[B]5% - 6%",0.06,0.05,"B"));
		roiList.add(new FundROI("[B]6% - 7%",0.07,0.06,"B"));
		roiList.add(new FundROI("[B]7% - 8%",0.08,0.07,"B"));
		roiList.add(new FundROI("[B]8% - 9%",0.09,0.08,"B"));
		roiList.add(new FundROI("[B]9% - 10%",0.1,0.09,"B"));
		roiList.add(new FundROI("[B]10% +",1,0.1,"B"));
		
		roiList.add(new FundROI("[B]0% - -1%",0,-0.01,"B"));
		roiList.add(new FundROI("[B]-1% - -2%",-0.01,-0.02,"B"));
		roiList.add(new FundROI("[B]-2% - -3%",-0.02,-0.03,"B"));
		roiList.add(new FundROI("[B]-3% - -4%",-0.03,-0.04,"B"));
		roiList.add(new FundROI("[B]-4% - -5%",-0.04,-0.05,"B"));
		roiList.add(new FundROI("[B]-5% - -6%",-0.05,-0.06,"B"));
		roiList.add(new FundROI("[B]-6% - -7%",-0.06,-0.07,"B"));
		roiList.add(new FundROI("[B]-7% - -8%",-0.07,-0.08,"B"));
		roiList.add(new FundROI("[B]-8% - -9%",-0.08,-0.09,"B"));
		roiList.add(new FundROI("[B]-9% - -10%",-0.09,-0.1,"B"));
		roiList.add(new FundROI("[B]-10% +",-0.1,-1,"B"));
		*/
		
		//double stepDelta=0.02;
		double upper = 0.1;
		double step=0.01;
		
		//for(double step=0.01; step<upper; step=step+stepDelta)
		//{
			int time = (int) (upper / step);
			for(int i = 0; i<time; i++)
			{
				roiList.add(new FundROI(step*(i+1),step*i,"A",true));
				roiList.add(new FundROI(step*(i+1),step*i,"B",true));
				
				roiList.add(new FundROI(-1*step*i,-1*step*(i+1),"A",true));
				roiList.add(new FundROI(-1*step*i,-1*step*(i+1),"B",true));
				
				roiList.add(new FundROI(step*(i+1),step*i,"A",false));
				roiList.add(new FundROI(step*(i+1),step*i,"B",false));
				
				roiList.add(new FundROI(-1*step*i,-1*step*(i+1),"A",false));
				roiList.add(new FundROI(-1*step*i,-1*step*(i+1),"B",false));
			}
			
			roiList.add(new FundROI(1,step*time,"A",true));
			roiList.add(new FundROI(1,step*time,"B",true));
			
			roiList.add(new FundROI(-1*step*time,-1,"A",true));
			roiList.add(new FundROI(-1*step*time,-1,"B",true));
			
			roiList.add(new FundROI(1,step*time,"A",false));
			roiList.add(new FundROI(1,step*time,"B",false));
			
			roiList.add(new FundROI(-1*step*time,-1,"A",false));
			roiList.add(new FundROI(-1*step*time,-1,"B",false));
		//}
		
		return roiList;
	}
	
}
