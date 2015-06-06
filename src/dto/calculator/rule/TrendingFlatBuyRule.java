package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class TrendingFlatBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		double rate = (thisStock.getStockEstimatedCost() - thisStock.getStockMACDFastEMA()) / thisStock.getStockMACDFastEMA(); 
		
		if(rate > RuleConfig.getInstance().getBuyRuleTrendingFlatRate())
			result = true;
		
		return result;
	}
}
