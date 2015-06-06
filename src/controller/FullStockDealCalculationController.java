package controller;

import java.util.ArrayList;
import java.util.Date;

import utility.date.DateUtil;
import utility.log.LoggerManager;
import model.Stock;
import model.StockDeal;
import dao.StockDBDAO;
import dao.StockDealDBDAO;
import dto.StockDealDTO;

public class FullStockDealCalculationController extends Controller {

	@Override
	public void action()
	{
		// ini
		StockDBDAO stockDbDao = new StockDBDAO();
		StockDealDBDAO stockDealDao = new StockDealDBDAO();
		StockDealDTO stockDealDto = new StockDealDTO();
		ArrayList <Stock> stockList = null;
		ArrayList <StockDeal> sellDealList = null;
		StockDeal buyDeal = null;
		
		// remove existing deals
		stockDealDao.removeAllStockDeal();
		
		// get date list in asc way
		ArrayList <Date> dateList = stockDbDao.getAscStockDateList();
		int dateCounter = dateList.size();
		
		// iterate date
		for(Date date : dateList)
		{
			stockList = stockDbDao.getStockListBaseOnDate(date);
			//stockList = new ArrayList <Stock>();
			//stockList.add(stockDbDao.getStockBaseOnStockIdAndDate("sz000788", date));
			
			for(Stock stock : stockList)
			{	
				// sell deals
				sellDealList = stockDealDto.transformSellStockDealFromStockData(stock);
				for(StockDeal sellDeal : sellDealList)
				{
					stockDealDao.updateSellDeal(sellDeal);
				}
				
				// buy deals
				buyDeal = stockDealDto.transformBuyStockDealFromStockData(stock);
				if(buyDeal != null)	
				{
					stockDealDao.insertBuyDeal(buyDeal);
				}
			}
			
			dateCounter--;
			LoggerManager.getInstance().getLogger().info("Full Stock Deal Runner: processed date:"+DateUtil.format(date)+"; To be processed date no.:"+dateCounter);
		}
		
	}

}
