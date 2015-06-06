package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class UpperGreaterLowerDevStockBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		if(thisStock.getStockBollUpper() > thisStock.getStockBollLower())
			result = true;
		
		return result;
	}

}
