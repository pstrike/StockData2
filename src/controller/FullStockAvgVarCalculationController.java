package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Stock;
import utility.date.DateUtil;
import utility.log.LoggerManager;
import dao.StockDBDAO;

public class FullStockAvgVarCalculationController extends Controller {

	@Override
	public void action()
	{
		// ini
		StockDBDAO stockDbDao = new StockDBDAO();
		Stock stock = null;
		
		// get date list in asc way
		ArrayList <Date> dateList = stockDbDao.getAscStockDateList();
		int dateCounter = dateList.size();
		
		// iterate date
		for(Date date : dateList)
		{
			stock = new Stock();
			stock.setStockId("overall");
			stock.setStockDate(date);
			stock.setStockPriceCostVar(stockDbDao.getOverallStockVarAverage(date));
			
			stockDbDao.insertRawStock(stock);
			
			dateCounter--;
			LoggerManager.getInstance().getLogger().info("Full Stock Deal Runner: processed date:"+DateUtil.format(date)+"; To be processed date no.:"+dateCounter);
		}
		
	}
}
