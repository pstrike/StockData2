package controller;

import java.util.ArrayList;

import model.Stock;
import utility.log.LoggerManager;
import dao.StockDBDAO;
import dto.StockDTO;

public class FullStockTBOLLController extends Controller{

	@Override
	public void action()
	{
		StockDBDAO stockDbDao = new StockDBDAO();
		StockDTO stockDto = new StockDTO();
		ArrayList<String> stockIdList = null;
		ArrayList<Stock> stockList = null;
		String stockId = null;
		
		stockIdList = stockDbDao.getAllStockId();
		
		for(int i=0; i<stockIdList.size(); i++)
		{
			stockId = stockIdList.get(i);
			stockList = stockDbDao.getAscStockListBaseOnStockId(stockId);
			
			stockDto.transformStockListForBOLL(stockList);
			
			for(Stock stock : stockList)
			{
				stockDbDao.updateStock(stock);
			}
			
			LoggerManager.getInstance().getLogger().info("Full Stock BOLL Transformation - complete:"+stockId+"; pending: "+(stockIdList.size()-i-1));
		}
	}

}