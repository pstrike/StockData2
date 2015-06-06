package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public class MovingSellRule extends StockCalculationRule {

	static double movingSellRateList [][] = {
									{-99,0.03},
									/*{-99,-0.05},
									{0,0.015},
									{0.05,0.035},
									{0.08,0.065},
									{0.1,0.085},
									{0.15,0.13},
									{0.2,0.175},
									{0.25,0.22},
									{0.3,0.26},
									{0.35,0.31},
									{0.4,0.36},
									{0.45,0.41},
									{0.5,99}*/
								   	};
	
	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal)
	{
		boolean result = false;
		
		if(thisStock.getStockAvg() < thisStock.getStockWaveLastOneAvg())
		{
			double roi = (thisStock.getStockAvg() - deal.getBuyPrice()) / deal.getBuyPrice();
			
			for(int i=movingSellRateList.length-1; i>-1; i--)
			{
				if(deal.getBuyVar() > movingSellRateList[i][0])
				{
					if(roi<movingSellRateList[i][1])
					{
						result = true;
					}
					
					break;
				}
			}
		}
		return result;
	}

}