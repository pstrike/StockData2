package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class MACDGoldCrossStockBuyRule extends StockCalculationRule {

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal)
	{
		boolean result = false;
		
		if(thisStock.getStockMACD() >=0 && thisStock.getStockMACDLastOne() < 0)
		{
			result = true;
		}
		
		return result;
	}

}