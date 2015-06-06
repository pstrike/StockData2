package controller;

import java.util.ArrayList;
import java.util.Date;

import utility.date.DateUtil;
import utility.log.LoggerManager;
import model.Stock;
import dao.StockDBDAO;
import dto.StockDTO;

public class FullStockTOverallAvgVarController extends Controller{

	@Override
	public void action()
	{
		// ini
		StockDBDAO stockDbDao = new StockDBDAO();
		StockDTO stockDto = new StockDTO();
		ArrayList <Stock> stockList = null;
		
		// get date list in asc way
		ArrayList <Date> dateList = stockDbDao.getAscStockDateList();
		int dateCounter = dateList.size();		
		
		// iterate date
		for(Date date : dateList)
		{
			stockList = stockDbDao.getStockListBaseOnDate(date);
			
			stockDto.transformStockListForOverallAvgVar(stockList, date);
			
			for(Stock stock : stockList)
			{
				stockDbDao.updateStock(stock);
			}
			
			dateCounter--;
			LoggerManager.getInstance().getLogger().info("Full Stock Transformation Overall Avg Var Runner: processed date:"+DateUtil.format(date)+"; To be processed date no.:"+dateCounter);
		}
		
	}

}
