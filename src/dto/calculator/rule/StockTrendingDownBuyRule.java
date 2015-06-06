package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class StockTrendingDownBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		if(thisStock.getStockUpDownPct() > RuleConfig.getInstance().getTrendingPct())
			result=true;
		
		return result;
	}

}
