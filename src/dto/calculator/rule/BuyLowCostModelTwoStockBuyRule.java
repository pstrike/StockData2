package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class BuyLowCostModelTwoStockBuyRule extends StockCalculationRule {

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal)
	{
		boolean result = false;
		
		//double thisVar = (thisStock.getStockAvg() - thisStock.getStockEstimatedCost() * 0.9) / (thisStock.getStockEstimatedCost() * 0.9); 
		
		/*
		double lastVar = thisStock.getStockLowVariance() - thisStock.getStockLowVarianceDelta();
		
		if(thisStock.getStockLowVariance() > 0 && lastVar < 0)
		//if(thisStock.getStockLowVariance() > 0)
		{
			result = true;
		}
		*/
		
		
		if(thisStock.getStockLowVariance() > 0 
			&& thisStock.getStockWaveLastTwoAvg() > thisStock.getStockWaveLastOneAvg() 
			&& thisStock.getStockWaveLastOneAvg() < thisStock.getStockAvg()
			&& thisStock.getStockWaveLastOneLowAvg() > thisStock.getStockWaveLastTwoLowAvg())
		{
			result = true;
		}
		
		if(thisStock.getStockLowVariance() > 0 
			&& thisStock.getStockWaveLastOneAvg() < thisStock.getStockWaveLastTwoLowAvg() 
			&& thisStock.getStockAvg() > thisStock.getStockWaveLastTwoLowAvg())
		{
			result = true;
		}
		
		
		return result;
	}

}