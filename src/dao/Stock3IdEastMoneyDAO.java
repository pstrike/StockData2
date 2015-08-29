package dao;

import java.util.ArrayList;

import model.Stock3Id;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utility.log.LoggerManager;

public class Stock3IdEastMoneyDAO {
	private static final String EM_STOCK_ID_LIST_URL = "http://quote.eastmoney.com/stock_list.html";
	private static final String EM_STOCK_CATEGORY_URL = "http://quote.eastmoney.com/[STOCKCODE].html";
	private static final String STOCK_CODE_IND = "[STOCKCODE]";
	
	public ArrayList<Stock3Id> getStock3Id()
	{
		ArrayList<Stock3Id> result = new ArrayList<Stock3Id>();
		Stock3Id stockId = null;
		
		try
		{
			Document doc =  Jsoup.connect(EM_STOCK_ID_LIST_URL).get();
            
            Element stockIdListDiv = doc.getElementById("quotesearch");
            Elements stockIdListUls = stockIdListDiv.getElementsByTag("ul");
            
            Element shStockIdListUl = stockIdListUls.get(0);
            Elements shStockIdListLis = shStockIdListUl.getElementsByTag("li");
            for (Element li : shStockIdListLis)
            {
            	String item [] = li.text().split("\\(");
            	
            	stockId = new Stock3Id();
            	stockId.setName(item[0]);
            	stockId.setId(item[1].substring(0, item[1].length()-1));
            	stockId.setMarket("sh");
            	
            	result.add(stockId);
            }
            
            Element szStockIdListUl = stockIdListUls.get(1);
            Elements szStockIdListLis = szStockIdListUl.getElementsByTag("li");
            for (Element li : szStockIdListLis)
            {
            	String item [] = li.text().trim().split("\\(");
            	
            	stockId = new Stock3Id();
            	stockId.setName(item[0]);
            	stockId.setId(item[1].substring(0, item[1].length()-1));
            	stockId.setMarket("sz");
            	
            	result.add(stockId);
            }
		}
        catch (Exception ex)  
        {
        	LoggerManager.getInstance().getLogger().error("Get Stock 3 Id from East Money Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        }
		
		return result;
	}
	
	public String getStock3Category(Stock3Id stockId)
	{
		String result = "";
		
		try
		{
			Document doc =  Jsoup.connect(generateStockCategoryURL(stockId.getMarket()+stockId.getId())).get();
            
			Elements stockCategoryDivs = doc.getElementsByClass("nav");
			
			if(stockCategoryDivs.size()>0)
			{
				Elements stockCategoryAs = stockCategoryDivs.get(0).getElementsByTag("a");
				
				if(stockCategoryAs.size()>=3)
				{
					result = stockCategoryAs.get(2).text().trim();
				}
			}
		}
        catch (Exception ex)  
        {
        	LoggerManager.getInstance().getLogger().error("Get Stock 3 Category from East Money Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        }
		
		return result;
	}
	
	private String generateStockCategoryURL(String stockId)
	{
		return EM_STOCK_CATEGORY_URL.replace(STOCK_CODE_IND, stockId);
	}
}
