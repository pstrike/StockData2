package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class BadSellLowCostStockSellRule extends StockCalculationRule {

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal)
	{
		boolean result = false;
		
		double lastVar = thisStock.getStockLowVariance() - thisStock.getStockLowVarianceDelta();
		
		if(thisStock.getStockLowVariance() < 0 && lastVar > 0)
		{
			result = true;
		}
		
		return result;
	}

}
