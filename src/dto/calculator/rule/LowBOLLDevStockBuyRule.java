package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class LowBOLLDevStockBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		//double dev = (thisStock.getStockBollUpper() - thisStock.getStockBollMiddle())/2;
		
		double rate = thisStock.getStockBollMiddle() / thisStock.getStockLowEstimatedCost();
		
		if(rate > RuleConfig.getInstance().getLowDevRate())
			result = true;
		
		return result;
	}

}
