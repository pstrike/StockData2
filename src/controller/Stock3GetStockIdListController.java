package controller;

import java.util.ArrayList;
import model.Stock3Id;
import utility.log.LoggerManager;
import dao.Stock3IdDBDAO;
import dao.Stock3IdEastMoneyDAO;

public class Stock3GetStockIdListController extends Controller{

	@Override
	public void action()
	{
		try
		{
			Stock3IdEastMoneyDAO stockIdEMDao = new Stock3IdEastMoneyDAO();
			ArrayList<Stock3Id> stockIdList = null;
			
			Stock3IdDBDAO stockIdDbDao = new Stock3IdDBDAO();
			ArrayList<Stock3Id> stockIdListInDB = stockIdDbDao.getAllStockId();
			
			stockIdList = stockIdEMDao.getStock3Id();
			
			for(Stock3Id stockId : stockIdList)
			{
				if(!stockIdListInDB.contains(stockId))
				{
					stockId.setCategory(stockIdEMDao.getStock3Category(stockId));
					stockId.setType(getStockType(stockId));
					
					stockIdDbDao.insertStockId(stockId);
					LoggerManager.getInstance().getLogger().info("Insert Stock 3 Id: "+stockId.getId());
				}
			}
		}
		catch (Exception ex)
        {
        	LoggerManager.getInstance().getLogger().error("Get Stock 3 Id Controller Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        }
		
	}
	
	private String getStockType(Stock3Id stockId)
	{
		String result = "";
		
		String [][] mapping = new String [][] {{"sh","0","国债/指数"},
												{"sh","1","债券"},
												{"sh","2","回购"},
												{"sh","3","期货"},
												{"sh","4","备用"},
												{"sh","5","基金/权证"},
												{"sh","6","A股"},
												{"sh","7","非交易业务"},
												{"sh","8","备用"},
												{"sh","9","B股"},
												{"sz","0","A股"},
												{"sz","1","基金"},
												{"sz","2","B股"},
												{"sz","3","创业板"},
												{"sz","4","代办转让股票"}};
		
		for(int i=0; i<mapping.length; i++)
		{
			if(mapping[i][0].equals(stockId.getMarket()) && mapping[i][1].equals(stockId.getId().subSequence(0, 1)))
			{
				result = mapping[i][2];
				break;
			}
		}
		
		return result;
	}

}
