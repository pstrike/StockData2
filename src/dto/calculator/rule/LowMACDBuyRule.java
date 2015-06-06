package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class LowMACDBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		if(thisStock.getStockMACD() > RuleConfig.getInstance().getBuyRuleLowestMACD())
			result = true;
		
		return result;
	}
}

