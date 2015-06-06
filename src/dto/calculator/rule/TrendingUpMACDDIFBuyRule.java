package dto.calculator.rule;

import dao.StockDBDAO;
import model.Stock;
import model.StockDeal;

public class TrendingUpMACDDIFBuyRule extends StockCalculationRule{

	@Override
	public boolean executeRule(Stock thisStock, StockDeal deal) 
	{
		boolean result = false;
		
		StockDBDAO stockDbDao = new StockDBDAO();
		
		if(thisStock.getStockMACDDIF() > stockDbDao.getHighetMACDDIFInLastWAVEByDate(thisStock.getStockId(),thisStock.getStockDate()))
			result = true;
		
		return result;
	}
}
