package controller;

import java.util.ArrayList;

import model.Stock;
import utility.log.LoggerManager;
import dao.StockDBDAO;

public class FullStockTWaveCalculationController extends Controller{

	@Override
	public void action()
	{
		StockDBDAO stockDbDao = new StockDBDAO();
		ArrayList<String> stockIdList = null;
		ArrayList<Stock> stockList = null;
		String stockId = null;
		Stock lastOneStock = null;
		Stock lastTwoStock = null;
		
		stockIdList = stockDbDao.getAllStockId();
		
		for(int i=0; i<stockIdList.size(); i++)
		{
			stockId = stockIdList.get(i);
			stockList = stockDbDao.getAscStockListBaseOnStockId(stockId);
			
			lastOneStock = null;
			lastTwoStock = null;
			
			for(Stock stock : stockList)
			{
				if(stock.getStockWaveLastOneLowAvg()==0)
					stock.setStockWaveLastOneLowAvg(99999);
				
				if(stock.getStockWaveLastTwoLowAvg()==0)
					stock.setStockWaveLastTwoLowAvg(99999);
				
				// high wave
				if(lastOneStock!=null && lastTwoStock!=null && stock.getStockAvg() > stock.getStockLowEstimatedCost())
				{
					if(stock.getStockAvg()>lastOneStock.getStockWaveHighAvg())
					{
						stock.setStockWaveHighAvg(stock.getStockAvg());
					}
					else
					{
						stock.setStockWaveHighAvg(lastOneStock.getStockWaveHighAvg());
					}
				}
				else
				{
					stock.setStockWaveHighAvg(0);
				}
				
				// calculate low wave
				if(lastOneStock!=null && lastTwoStock!=null)
				{
					if(lastTwoStock.getStockAvg() > lastOneStock.getStockAvg()
						&& lastOneStock.getStockAvg() < stock.getStockAvg())
					{
						stock.setStockWaveLastTwoLowAvg(lastOneStock.getStockWaveLastOneLowAvg());
						stock.setStockWaveLastOneLowAvg(lastOneStock.getStockAvg());
					}
					else
					{
						stock.setStockWaveLastOneLowAvg(lastOneStock.getStockWaveLastOneLowAvg());
						stock.setStockWaveLastTwoLowAvg(lastOneStock.getStockWaveLastTwoLowAvg());
					}
				}
				
				if(lastOneStock!=null)
				{
					stock.setStockWaveLastOneAvg(lastOneStock.getStockAvg());
				}
				
				if(lastTwoStock!=null)
				{
					stock.setStockWaveLastTwoAvg(lastTwoStock.getStockAvg());
				}
				
				stockDbDao.updateStock(stock);
				
				lastTwoStock = lastOneStock;
				lastOneStock = stock;		
			}
			
			LoggerManager.getInstance().getLogger().info("Full Stock Wave Calculation - complete:"+stockId+"; pending: "+(stockIdList.size()-i-1));
		}
	}

}