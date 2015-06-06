package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class GoodSellStockSellRule extends StockCalculationRule {

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal)
	{
		boolean result = false;
		
		double roi = (thisStock.getStockAvg() - deal.getBuyPrice()) / deal.getBuyPrice();
		
		if(roi > RuleConfig.getInstance().getGoodToSell())
		{
			result = true;
		}
		
		return result;
	}

}
