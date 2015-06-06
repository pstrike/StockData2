package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Stock;
import utility.date.DateUtil;
import utility.log.LoggerManager;
import dao.StockDBDAO;
import dao.StockFTPDAO;
import dto.StockDTO;

public class DailyStockProcessController extends Controller {

	@Override
	public void action()
	{
		// initialization
		StockFTPDAO stockFtpDao = new StockFTPDAO();
		StockDBDAO stockDbDao = new StockDBDAO();
		StockDTO stockDto = new StockDTO();
		ArrayList <Date> toProcessDates = new ArrayList<Date>();
		ArrayList <Stock> stockList = null;
		Date toProcessDate = null;
		int counter = 0;
		
		try {
			// figure out to be processed date
			toProcessDate = DateUtil.addDay(stockDbDao.getLatestStockDate(),1);
			Date endDate = DateUtil.addDay(DateUtil.parse(DateUtil.getToday()),1);
			
			while(toProcessDate.before(endDate))
			{
				toProcessDates.add(toProcessDate);
				toProcessDate = DateUtil.addDay(toProcessDate, 1);
			}
			
			// begin ETL base on to be processed date
			counter = toProcessDates.size(); // counter is used as progress indicator
			while(toProcessDates.size()>0)
			{
				toProcessDate = toProcessDates.get(0);
				
				// E SH stock data
				stockList = stockFtpDao.getStockAtDate(toProcessDate, StockFTPDAO.SH_STOCK);
				stockList.addAll(stockFtpDao.getStockAtDate(toProcessDate, StockFTPDAO.SZ_STOCK));
				
				for(Stock stock : stockList)
				{
					// T stock data
					stockDto.transformStock(stock);
					
					// L Stock data
					//stockDbDao.insertRawStock(stock);
					LoggerManager.getInstance().getLogger().info(stock.getStockId()+":"+stock.getStockEstimatedCost());
				}
				
				counter--;
				LoggerManager.getInstance().getLogger().info("Daily Stock Processing:"+DateUtil.format(toProcessDate)+" completed; To be processed date:"+counter);
				toProcessDates.remove(0);
			}
			
		} catch (Exception e) {
			LoggerManager.getInstance().getLogger().error("Daily Stock Processing Exception");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}

	}

}
