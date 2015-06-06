package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class StockCostVarAndMovingAvgVarDistanceBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		if((thisStock.getStockMovingPriceCostVarAvg() - thisStock.getStockPriceCostVar()) > RuleConfig.getInstance().getVarMovingVarDistance())
			result = true;
		
		return result;
	}

}
