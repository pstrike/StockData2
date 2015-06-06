package dto.calculator;

import java.util.ArrayList;

import model.Stock;
import dto.calculator.rule.RuleConfig;

public class StockBOLLCalculator {
	
	public void calculateBOLL(ArrayList<Stock> stockList)
	{
		double bollDay = RuleConfig.getInstance().getBollDay();
		Stock stock = null;
		Stock backStock = null;
				
		for(int i=0; i<stockList.size(); i++)
		{
			stock = stockList.get(i);
			
			double sma = 0;
			double total = 0;
			double stdevTemp = 0;
			double stdevUpTemp = 0;
			double stdevDownTemp = 0;
			double stdev = 0;
			double stdevUp = 0;
			double stdevDown = 0;
			int upNo = 0;
			
			if(i >= bollDay-1)
			{
				
				for(int j= i; j>i-bollDay; j--)
				{
					backStock = stockList.get(j);
					total+= backStock.getStockAvg();
				}
				
				sma = total / bollDay;
				
				for(int j= i; j>i-bollDay; j--)
				{
					backStock = stockList.get(j);
					
					if(backStock.getStockAvg() > sma)
					{
						stdevUpTemp+= (backStock.getStockAvg() - sma) * (backStock.getStockAvg() - sma);
						upNo++;
					}
					else
					{
						stdevDownTemp+= (backStock.getStockAvg() - sma) * (backStock.getStockAvg() - sma);
					}
					
					stdevTemp+= (backStock.getStockAvg() - sma) * (backStock.getStockAvg() - sma);
				}
				
				stdev = Math.sqrt(stdevTemp / bollDay);
				
				if(upNo > 0)
					stdevUp = Math.sqrt(stdevUpTemp / upNo);
				else
					stdevUp = 0;
				
				if(upNo == bollDay)
					stdevDown = 0;
				else
					stdevDown = Math.sqrt(stdevDownTemp / (bollDay-upNo));
			}
			
			//stock.setStockBollMiddle(sma);
			stock.setStockBollMiddle(stdev);
			//stock.setStockBollUpper(sma + stdev*2);
			//stock.setStockBollLower(sma - stdev*2);
			stock.setStockBollUpper(stdevUp);
			stock.setStockBollLower(stdevDown);
		}
	}
	
}