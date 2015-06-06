package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class WaveTrendDownStockSellRule extends StockCalculationRule {
	
	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal)
	{
		boolean result = false;
		
		if(thisStock.getStockAvg() < thisStock.getStockWaveLastOneAvg())
		{
			double roi = (thisStock.getStockAvg() - deal.getBuyPrice()) / deal.getBuyPrice();
			
			if(roi < -0.03)
				result = true;
			else if (roi > 0 && roi < deal.getBuyVar() * 0.9)
				result = true;
		}
		return result;
	}

}