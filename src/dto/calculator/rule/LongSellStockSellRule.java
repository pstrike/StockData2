package dto.calculator.rule;

import utility.date.DateUtil;
import model.Stock;
import model.StockDeal;

public class LongSellStockSellRule extends StockCalculationRule {

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal)
	{
		boolean result = false;
		
		double varDistance = thisStock.getStockPriceCostVar() - deal.getBuyVar();
		
		int dateDistance = DateUtil.dateSubtract(thisStock.getStockDate(), deal.getBuyDate());
		
		if(varDistance > RuleConfig.getInstance().getLongToSell() && dateDistance > RuleConfig.getInstance().getLongToSellDay())
			result = true;
		
		return result;
	}

}