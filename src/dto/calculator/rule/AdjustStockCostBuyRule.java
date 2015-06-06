package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class AdjustStockCostBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		double adjustCost = 0;
		
		if(thisStock.getStockMovingPriceCostVarAvg() > (RuleConfig.getInstance().getAdjustCostHigHestRate() - RuleConfig.getInstance().getAdjustCostRate()))
			adjustCost = thisStock.getStockEstimatedCost();
		else
			adjustCost  = (RuleConfig.getInstance().getAdjustCostRate() + thisStock.getStockMovingPriceCostVarAvg()) * thisStock.getStockEstimatedCost();
			
		double rate = (thisStock.getStockAvg() -  adjustCost) / adjustCost;
		
		if(rate < RuleConfig.getInstance().getLowToBuyUpper() && rate > RuleConfig.getInstance().getLowToBuyLower())
			result = true;
		
		return result;
	}

}
