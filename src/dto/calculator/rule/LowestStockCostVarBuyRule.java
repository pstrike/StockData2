package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class LowestStockCostVarBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		if(thisStock.getStockPriceCostVar() > thisStock.getStockMovingPriceCostVarAvg()*RuleConfig.getInstance().getLowestVarRate())
			result = true;
		
		return result;
	}

}
