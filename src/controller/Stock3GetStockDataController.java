package controller;

import java.util.ArrayList;

import model.Stock3Data;
import model.Stock3Id;
import utility.log.LoggerManager;
import dao.Stock3DataDBDAO;
import dao.Stock3DataTecentDAO;
import dao.Stock3IdDBDAO;

public class Stock3GetStockDataController extends Controller{

	@Override
	public void action()
	{
		try
		{
			Stock3IdDBDAO stockIdDbDao = new Stock3IdDBDAO();
			ArrayList<Stock3Id> stockIdListInDB = stockIdDbDao.getAllStockId();
			
			// for debug
			/*ArrayList<Stock3Id> stockIdListInDB = new ArrayList<Stock3Id>();
			Stock3Id id = new Stock3Id();
			id.setId("600075");
			id.setMarket("sh");
			stockIdListInDB.add(id);*/
			
			Stock3DataTecentDAO stockDataTcDao = new Stock3DataTecentDAO();
			Stock3Data stockData = null;
			
			Stock3DataDBDAO stockDataDbDao = new Stock3DataDBDAO();
			
			for(Stock3Id stockId : stockIdListInDB)
			{
				stockData = stockDataTcDao.getStockData(stockId.getMarket()+stockId.getId());
				if(stockData!=null)
				{
					stockDataDbDao.insertStockData(stockData);
					LoggerManager.getInstance().getLogger().info("Get Stock 3 Data: "+stockId.getMarket()+stockId.getId());
				}
				
				
			}
		}
		catch (Exception ex)
        {
        	LoggerManager.getInstance().getLogger().error("Get Stock 3 Data Controller Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        }
		
	}

}