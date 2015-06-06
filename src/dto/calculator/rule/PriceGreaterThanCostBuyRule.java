package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class PriceGreaterThanCostBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		if(thisStock.getStockAvg() > thisStock.getStockEstimatedCost())
			result = true;
		
		return result;
	}
}
