package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class BuyStockBuyRule extends StockCalculationRule {

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal)
	{
		boolean result = false;
		
		//double thisVar = (thisStock.getStockAvg() - thisStock.getStockEstimatedCost() * 0.9) / (thisStock.getStockEstimatedCost() * 0.9); 
		
		double lastVar = thisStock.getStockPriceCostVar() - thisStock.getStockPriceCostVarDelta();
		
		if(thisStock.getStockPriceCostVar() > 0 && lastVar < 0)
		{
			result = true;
		}
		
		return result;
	}

}
