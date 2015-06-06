package controller;

import java.util.ArrayList;

import model.Stock;
import utility.log.LoggerManager;
import dao.StockDBDAO;

public class FullStockVarDeltaCalculatorController extends Controller{

	@Override
	public void action()
	{
		StockDBDAO stockDbDao = new StockDBDAO();
		ArrayList<String> stockIdList = null;
		ArrayList<Stock> stockList = null;
		String stockId = null;
		double previousVar = 0;
		
		stockIdList = stockDbDao.getAllStockId();
		
		for(int i=0; i<stockIdList.size(); i++)
		{
			stockId = stockIdList.get(i);
			stockList = stockDbDao.getAscStockListBaseOnStockId(stockId);
			
			previousVar = 0;
			
			for(Stock stock : stockList)
			{
				stock.setStockPriceCostVarDelta(stock.getStockPriceCostVar() - previousVar);
				stockDbDao.updateStock(stock);
				
				previousVar = stock.getStockPriceCostVar();
			}
			
			LoggerManager.getInstance().getLogger().info("Full Stock Delta Var Calculation - complete:"+stockId+"; pending: "+(stockIdList.size()-i-1));
		}
	}

}
