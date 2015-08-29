package runner;

import java.text.ParseException;
import java.util.ArrayList;

import autodeal.Broker;
import autodeal.DealBuy;
import autodeal.DealSell;
import autodeal.Holding;
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
		
		
		GetFundDataController sc = new GetFundDataController();
		sc.action(null, null);
		*/
		
		testAutoDeal();
	}
	
	public static void testAutoDeal()
	{
		ArrayList<DealBuy> buyDeals = new ArrayList<DealBuy>();
		
		DealBuy buyDeal1 = new DealBuy();
		buyDeal1.setId("000001");
		buyDeal1.setPrice(13.5);
		buyDeal1.setVolume(500);
		
		buyDeals.add(buyDeal1);
		
		DealBuy buyDeal2 = new DealBuy();
		buyDeal2.setId("000002");
		buyDeal2.setPrice(23.5);
		buyDeal2.setVolume(200);
		
		buyDeals.add(buyDeal2);
		
		ArrayList<DealSell> sellDeals = new ArrayList<DealSell>();
		
		DealSell sellDeal1 = new DealSell();
		sellDeal1.setId("000003");
		sellDeal1.setPrice(3.5);
		sellDeal1.setVolume(300);
		
		sellDeals.add(sellDeal1);
		
		DealSell sellDeal2 = new DealSell();
		sellDeal2.setId("000004");
		sellDeal2.setPrice(50.5);
		sellDeal2.setVolume(2000);
		
		sellDeals.add(sellDeal2);
		
		Broker broker = new Broker();
		broker.buy(buyDeals);
		broker.sell(sellDeals);
		
		ArrayList<Holding> holdings = broker.getBalance();
		for(Holding holding : holdings)
		{
			System.out.println(holding.getId());
		}
	}
	
}
