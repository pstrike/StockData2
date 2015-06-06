package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class HighestMACDDIFBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		if(thisStock.getStockMACDDIF() < thisStock.getStockAvg() * RuleConfig.getInstance().getBuyRuleHighestMACDDIFRate())
			result = true;
		
		return result;
	}
}

