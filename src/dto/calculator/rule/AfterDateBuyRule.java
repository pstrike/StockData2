package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class AfterDateBuyRule extends StockCalculationRule {

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal)
	{
		boolean result = false;
		
		if(thisStock.getStockDate().after(RuleConfig.getInstance().getBuyAfterDate()))
			result = true;
		
		return result;
	}

}
