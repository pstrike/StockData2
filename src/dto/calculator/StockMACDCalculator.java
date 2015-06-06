package dto.calculator;

import java.util.ArrayList;

import dto.calculator.rule.RuleConfig;
import model.Stock;

public class StockMACDCalculator {
	
	public void calculateMACD(ArrayList<Stock> stockList)
	{
		Stock previousStock = null;
				
		for(Stock stock : stockList)
		{
			calculateSpecificStockMACD(stock, previousStock);
			
			previousStock = stock;
		}
	}
	
	private void calculateSpecificStockMACD(Stock thisStock, Stock previousStock)
	{		
		if(previousStock == null)
		{
			thisStock.setStockMACDFastEMA(thisStock.getStockAvg());
			thisStock.setStockMACDSlowEMA(thisStock.getStockAvg());
			thisStock.setStockMACDDIF(thisStock.getStockMACDFastEMA() - thisStock.getStockMACDSlowEMA());
			thisStock.setStockMACDDEA(thisStock.getStockMACDFastEMA() - thisStock.getStockMACDSlowEMA());
			thisStock.setStockMACD(thisStock.getStockMACDDIF() - thisStock.getStockMACDDEA());
			thisStock.setStockMACDLastOne(0);
		}
		else
		{
			thisStock.setStockMACDFastEMA(thisStock.getStockAvg()*2.0/(RuleConfig.getInstance().getMACDFastEMA()/2.0)+previousStock.getStockMACDFastEMA()*(RuleConfig.getInstance().getMACDFastEMA()/2.0-2)/(RuleConfig.getInstance().getMACDFastEMA()/2.0));
			thisStock.setStockMACDSlowEMA(thisStock.getStockAvg()*2.0/(RuleConfig.getInstance().getMACDSlowEMA()/2.0)+previousStock.getStockMACDSlowEMA()*(RuleConfig.getInstance().getMACDSlowEMA()/2.0-2)/(RuleConfig.getInstance().getMACDSlowEMA()/2.0));
			
			thisStock.setStockMACDDIF(thisStock.getStockMACDFastEMA() - thisStock.getStockMACDSlowEMA());
			thisStock.setStockMACDDEA(thisStock.getStockMACDDIF()*2.0/(RuleConfig.getInstance().getMACDDEAEMA()/2.0)+previousStock.getStockMACDDEA()*(RuleConfig.getInstance().getMACDDEAEMA()/2.0-2)/(RuleConfig.getInstance().getMACDDEAEMA()/2.0));
			thisStock.setStockMACD(thisStock.getStockMACDDIF() - thisStock.getStockMACDDEA());
			thisStock.setStockMACDLastOne(previousStock.getStockMACD());
		}
	}
	
}
