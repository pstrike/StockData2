package dto.calculator;

import java.util.ArrayList;

import dto.calculator.rule.AdjustStockCostBuyRule;
import dto.calculator.rule.AfterDateBuyRule;
import dto.calculator.rule.BuyLowCostModelOneStockBuyRule;
import dto.calculator.rule.BuyLowCostModelTwoStockBuyRule;
import dto.calculator.rule.BuyStockBuyRule;
import dto.calculator.rule.HighestMACDDIFBuyRule;
import dto.calculator.rule.LowBOLLDevStockBuyRule;
import dto.calculator.rule.LowCostUpTrendStockBuyRule;
import dto.calculator.rule.LowMACDBuyRule;
import dto.calculator.rule.LowMACDDIFBuyRule;
import dto.calculator.rule.MACDGoldCrossStockBuyRule;
import dto.calculator.rule.PriceGreaterThanCostBuyRule;
import dto.calculator.rule.StockCalculationRule;
import dto.calculator.rule.StockTrendingDownBuyRule;
import dto.calculator.rule.TrendingFlatBuyRule;
import dto.calculator.rule.TrendingUpMACDDIFBuyRule;
import dto.calculator.rule.UpperGreaterLowerDevStockBuyRule;
import model.Stock;

public class BuyStockCalculator {
	public boolean isBuy(Stock stock)
	{
		boolean result = true;
		
		ArrayList <StockCalculationRule> rules = new ArrayList<StockCalculationRule> ();
		
		rules.add(new AfterDateBuyRule());
		//rules.add(new BuyStockBuyRule());
		rules.add(new BuyLowCostModelTwoStockBuyRule());
		//rules.add(new BuyLowCostModelOneStockBuyRule());
		//rules.add(new LowestStockCostVarBuyRule());
		//rules.add(new LowestStockMovingAvgVarBuyRule());
		//rules.add(new StockCostVarAndMovingAvgVarDistanceBuyRule());
		
		//rules.add(new AdjustStockCostBuyRule());
		//rules.add(new PriceGreaterThanCostBuyRule());
		//rules.add(new LowMACDDIFBuyRule());
		//rules.add(new LowMACDBuyRule());
		//rules.add(new HighestMACDDIFBuyRule());
		//rules.add(new StockTrendingDownBuyRule());
		//rules.add(new TrendingFlatBuyRule());
		//rules.add(new TrendingUpMACDDIFBuyRule());
		//rules.add(new LowBOLLDevStockBuyRule());
		//rules.add(new UpperGreaterLowerDevStockBuyRule());
		//rules.add(new LowCostUpTrendStockBuyRule());
		//rules.add(new MACDGoldCrossStockBuyRule());
		
		for(StockCalculationRule rule : rules)
    	{
			result = result && rule.executeRule(stock, null);
			
			if(result == false)
				break;
    	}		
		
		return result;
	}
}
