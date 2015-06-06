package dto;

import java.util.ArrayList;
import java.util.Date;

import dao.CapitalDBDAO;
import dao.StockDBDAO;
import dto.calculator.StockBOLLCalculator;
import dto.calculator.StockMACDCalculator;
import dto.calculator.rule.RuleConfig;
import model.Capital;
import model.Stock;

public class StockDTO {
	
	public void transformStockList(ArrayList <Stock> stockList)
	{
		Stock stock = null;
		Stock previousStock = null;
		double sumVarAvg = 0;
		int counter = 0;
		
		for(int i=0; i<stockList.size(); i++)
		{	
			stock = stockList.get(i);
			
			// set stock total volume and A issue volume data
			getStockVolume(stock);
			
			// basic stock data calculate
			basicProcessing(stock, previousStock);
			
			// moving average calculation
			if(counter < RuleConfig.getInstance().getMovingAvgVarBackTo())
			{
				counter++;
				sumVarAvg += stock.getStockPriceCostVar();
				stock.setStockMovingPriceCostVarAvg(sumVarAvg / counter);
			}
			else if(counter == RuleConfig.getInstance().getMovingAvgVarBackTo())
			{
				// new total = old total - 1st value + new
				sumVarAvg = sumVarAvg - stockList.get(i-RuleConfig.getInstance().getMovingAvgVarBackTo()).getStockPriceCostVar() + stock.getStockPriceCostVar();
				
				stock.setStockMovingPriceCostVarAvg(sumVarAvg / RuleConfig.getInstance().getMovingAvgVarBackTo());
			}
			
			// set stock buy index
			if(stock.getStockMovingPriceCostVarAvg() == 0)
				stock.setStockBuyIndex(0);
			else
				stock.setStockBuyIndex(stock.getStockPriceCostVar() / stock.getStockMovingPriceCostVarAvg());
			
			// set current stock as previous stock
			previousStock = stock;
		}
	}
	
	public void transformStock(Stock stock)
	{
		StockDBDAO stockDbDao = new StockDBDAO();
		Stock previousStock = stockDbDao.getLatestStockBaseOnStockId(stock.getStockId());
		Stock deductMovingAvgStock = stockDbDao.getStockBaseOnStockIdAtDayBefore(stock.getStockId(), RuleConfig.getInstance().getMovingAvgVarBackTo());
		double sumVarAvg = 0;
		
		// set stock total volume and A issue volume data
		getStockVolume(stock);
		
		// basic stock data calculate
		basicProcessing(stock, previousStock);
		
		// moving average calculation
		sumVarAvg =  previousStock.getStockMovingPriceCostVarAvg() * RuleConfig.getInstance().getMovingAvgVarBackTo() - deductMovingAvgStock.getStockPriceCostVar() + stock.getStockPriceCostVar();
		stock.setStockMovingPriceCostVarAvg(sumVarAvg / RuleConfig.getInstance().getMovingAvgVarBackTo());
		
		// set stock buy index
		if(stock.getStockMovingPriceCostVarAvg() == 0)
			stock.setStockBuyIndex(0);
		else
			stock.setStockBuyIndex(stock.getStockPriceCostVar() / stock.getStockMovingPriceCostVarAvg());
	}
	
	public void transformStockListForOverallAvgVar(ArrayList <Stock> stockList, Date date)
	{
		StockDBDAO stockDbDao = new StockDBDAO();
		
		double overallAvgVar = stockDbDao.getOverallStockVarAverage(date);
		
		for(Stock stock : stockList)
		{
			stock.setStockMovingPriceCostVarAvg(overallAvgVar);
		}
	}
	
	public void transformStockListForMACD(ArrayList <Stock> stockList)
	{
		StockMACDCalculator macdCalculator = new StockMACDCalculator();
		macdCalculator.calculateMACD(stockList);
	}
	
	public void transformStockListForBOLL(ArrayList <Stock> stockList)
	{
		StockBOLLCalculator bollCalculator = new StockBOLLCalculator();
		bollCalculator.calculateBOLL(stockList);
	}
	
	private void basicProcessing(Stock stock, Stock previousStock)
	{	
		// set stock average price
		if(stock.getStockVol()==0)
			stock.setStockAvg(0);
		else
			stock.setStockAvg(stock.getStockAmt()/stock.getStockVol());					
		
		// set stock turnover rate
		if(stock.getStockAIssueVol()==0)
			stock.setStockTurnoverRate(0);
		else
			stock.setStockTurnoverRate(stock.getStockVol()/stock.getStockAIssueVol());
		
		// set stock total amount base on close price
		stock.setStockTotalAmt(stock.getStockClose()*stock.getStockTotalVol());
		
		// set stock A issue amount base on close price
		stock.setStockAIssueAmt(stock.getStockClose()*stock.getStockAIssueVol());
		
		// base on previous Stock status to handle stockUpDownAmt, stockUpDownPct, stockEstimatedCost, stockPriceCostVar, stockMovingPriceCostVarAvg
		if(previousStock == null)
		{
			stock.setStockEstimatedCost(stock.getStockAvg());
		}
		else
		{			
			// set stock up down amount
			stock.setStockUpDownAmt(stock.getStockClose() - previousStock.getStockClose());
			
			// set stock up down percentage
			if(previousStock.getStockClose()==0)
				stock.setStockUpDownPct(0);
			else
				stock.setStockUpDownPct(stock.getStockUpDownAmt()/previousStock.getStockClose());
			
			// estimate cost calculation
			stock.setStockEstimatedCost(calculateEstimatedCost(stock, previousStock));
		}
		
		// set stock price cost variance
		stock.setStockPriceCostVar((stock.getStockAvg()-stock.getStockEstimatedCost())/stock.getStockEstimatedCost());
				
	}
	
	private void getStockVolume(Stock stock)
	{
		CapitalDBDAO capitalDbDao = new CapitalDBDAO();
		Capital capital = capitalDbDao.getCapitalAtDate(stock.getStockId(), stock.getStockDate());
		
		stock.setStockTotalVol(capital.getStockTotalVolume());
		stock.setStockAIssueVol(capital.getStockAIssueVolume());
	}
	
	private double calculateEstimatedCost(Stock stock, Stock previousStock)
	{
		double result = 0;
		
		// today - previous amt  = delta amt + jump open amt + updowm amt in the day
		double tempDeltaAmt = stock.getStockAIssueAmt() - previousStock.getStockAIssueAmt() - stock.getStockAIssueVol() * (stock.getStockClose() - stock.getStockOpen());
		double jumpOpenAmt = (stock.getStockOpen() - previousStock.getStockClose()) * stock.getStockAIssueVol();
		
		double deltaAmt = tempDeltaAmt;
		if((tempDeltaAmt - jumpOpenAmt) <= 1 && (tempDeltaAmt - jumpOpenAmt) >= -1)
		{
			deltaAmt = 0;
		}
		
		// this amt + (previous amt + delta amt) * (1 - turnover rate)
		double previousEstimatedAIssueAmt = previousStock.getStockEstimatedCost() * previousStock.getStockAIssueVol();
			
		double estimatedAIssueAmt = stock.getStockAmt() + (previousEstimatedAIssueAmt + deltaAmt) * (1 - stock.getStockTurnoverRate());
		
		result = estimatedAIssueAmt / stock.getStockAIssueVol();
		
		return result;
	}
}
