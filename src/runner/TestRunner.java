package runner;

import java.text.ParseException;
import java.util.ArrayList;

import model.StockDeal;
import model.StockROI;
import controller.FullStockROICalculationController;
import servlet.controller.GetFundDataController;
import utility.date.DateUtil;
import dao.StockDBDAO;
import dao.StockDealDBDAO;
import dto.calculator.rule.RuleConfig;

public class TestRunner {
	
	public static void main(String[] args) throws ParseException
	{
		/*
		System.out.println(RuleConfig.getInstance().getBadToSell());
		System.out.println(RuleConfig.getInstance().getLowToBuyUpper());
		System.out.println(RuleConfig.getInstance().getBuyAmt());
		System.out.println(RuleConfig.getInstance().getBuyVol());
		System.out.println(RuleConfig.getInstance().getGoodToSell());
		System.out.println(RuleConfig.getInstance().getLowestMovingAvgVar());
		System.out.println(RuleConfig.getInstance().getLowestVarRate());
		System.out.println(RuleConfig.getInstance().getMovingAvgVarBackTo());
		System.out.println(RuleConfig.getInstance().getTrendingPct());
		System.out.println(RuleConfig.getInstance().getVarMovingVarDistance());
		System.out.println(RuleConfig.getInstance().getAdjustCostRate());
		System.out.println(RuleConfig.getInstance().getAdjustCostHigHestRate());
		System.out.println(RuleConfig.getInstance().getBuyAfterDate());
		System.out.println(RuleConfig.getInstance().getMACDDEAEMA());
		System.out.println(RuleConfig.getInstance().getMACDFastEMA());
		System.out.println(RuleConfig.getInstance().getMACDSlowEMA());
		System.out.println(RuleConfig.getInstance().getBuyRuleHighestMACDDIFRate());
		System.out.println(RuleConfig.getInstance().getBuyRuleLowestMACD());
		System.out.println(RuleConfig.getInstance().getBuyRuleLowestMACDDIF());
		
		System.out.println(Math.ceil(3000/1400.00));
		
		System.out.println(DateUtil.dateSubtract(DateUtil.parse("2014-04-15"), DateUtil.parse("2014-04-10")));
		
		FullStockROICalculationController ctrl = new FullStockROICalculationController();
		ArrayList <StockROI> roiList = ctrl.generateROIList();
		
		for(StockROI roi : roiList)
		{
			System.out.println(roi.getStockId()+":"+roi.getStartDate()+"-"+roi.getEndDate());
		}
		
		StockDBDAO dao = new StockDBDAO();
				
		System.out.println(dao.getHighetMACDDIFInLastWAVEByDate("sz000571", DateUtil.parse("2007-08-06")));
		
		ArrayList <String> stringList = new ArrayList <String>();
		stringList.add("1");
		stringList.add("2");
		stringList.add("3");
		
		for(String s : stringList)
			System.out.println(s);
		*/
		
		GetFundDataController sc = new GetFundDataController();
		sc.action(null, null);
		
		
	}
	
}
