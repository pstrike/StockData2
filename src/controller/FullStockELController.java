package controller;

import java.util.ArrayList;

import utility.date.DateUtil;
import utility.log.LoggerManager;
import model.Stock;
import dao.StockCSVDAO;
import dao.StockDBDAO;

public class FullStockELController extends Controller {

	@Override
	public void action()
	{
		processStockDataETL(StockCSVDAO.SH_STOCK);
		processStockDataETL(StockCSVDAO.SZ_STOCK);
	}
	
	private void processStockDataETL(String type)
	{
		// initialization
		StockCSVDAO stockCsvDao = new StockCSVDAO();
		StockDBDAO stockDbDao = new StockDBDAO();
		ArrayList <Stock> stocks = null;
		int loadDataNo = 100000;
		int begin = 0;
		int end = 0;
		int total = 2300000;
		int counter = 0;

		while(end < total)
		{
			begin = end+1;
			end = begin + loadDataNo -1;
			
			// extraction
			stocks = stockCsvDao.getStocks(begin, end, type);
			
			// load
			for(Stock stock : stocks)
			{
				counter++;
				stockDbDao.insertRawStock(stock);
				LoggerManager.getInstance().getLogger().info("Full Stock EL Runner:"+DateUtil.format(stock.getStockDate())+";"+stock.getStockId()+";Processed stock no.:"+counter);
			}
		}
	}

}
