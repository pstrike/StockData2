package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class LowMACDDIFBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		if(thisStock.getStockMACDDIF() > RuleConfig.getInstance().getBuyRuleLowestMACDDIF())
			result = true;
		
		return result;
	}
}
