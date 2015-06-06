package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class LowestStockMovingAvgVarBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		if(thisStock.getStockMovingPriceCostVarAvg() > RuleConfig.getInstance().getLowestMovingAvgVar())
			result = true;
		
		return result;
	}
}
