package controller;

import java.util.ArrayList;

import model.Stock;
import utility.log.LoggerManager;
import dao.StockDBDAO;

public class FullStockLowEstimatedCostCalculationController extends Controller{

	@Override
	public void action()
	{
		StockDBDAO stockDbDao = new StockDBDAO();
		ArrayList<String> stockIdList = null;
		ArrayList<Stock> stockList = null;
		String stockId = null;
		double previousVar = 0;
		double previousLowCost = 0;
		
		stockIdList = stockDbDao.getAllStockId();
		
		for(int i=0; i<stockIdList.size(); i++)
		{
			stockId = stockIdList.get(i);
			stockList = stockDbDao.getAscStockListBaseOnStockId(stockId);
			
			previousVar = 0;
			
			for(Stock stock : stockList)
			{
				stock.setStockLowEstimatedCost(stock.getStockEstimatedCost()*0.9);
				if(stock.getStockLowEstimatedCost()>0)
					stock.setStockLowVariance((stock.getStockAvg()-stock.getStockLowEstimatedCost())/stock.getStockLowEstimatedCost());
				else
					stock.setStockLowVariance(0);
				stock.setStockLowVarianceDelta(stock.getStockLowVariance() - previousVar);
				stock.setStockLowEstimatedCostUpDownAmt(stock.getStockLowEstimatedCost() - previousLowCost);
				
				stockDbDao.updateStock(stock);
				
				previousVar = stock.getStockLowVariance();
				previousLowCost = stock.getStockLowEstimatedCost();
			}
			
			LoggerManager.getInstance().getLogger().info("Full Stock Low Estimated Cost Calculation - complete:"+stockId+"; pending: "+(stockIdList.size()-i-1));
		}
	}

}