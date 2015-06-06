package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class LowCostUpTrendStockBuyRule extends StockCalculationRule {

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal)
	{
		boolean result = false;
		
		if(thisStock.getStockLowEstimatedCostUpDownAmt() > 0)
		{
			result = true;
		}
		
		return result;
	}

}
