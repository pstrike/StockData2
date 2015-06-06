package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class BadSellStockSellRule extends StockCalculationRule {

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal)
	{
		boolean result = false;
		
		double lastVar = thisStock.getStockPriceCostVar() - thisStock.getStockPriceCostVarDelta();
		
		if(thisStock.getStockPriceCostVar() < 0 && lastVar > 0)
		{
			result = true;
		}
		
		return result;
	}

}
