package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import utility.log.LoggerManager;
import model.Fund;

public class FundSinaDAO
{
	private static final String TTJJURL = "http://hq.sinajs.cn/list=[STOCKCODE]";
	private static final String STOCK_CODE_IND = "[STOCKCODE]";
	
	public Fund getFundData(String stockId)
	{
		Fund result = null;
		
		try
		{
			String stockUrl = generateStockURL(stockId);
			
			URL url = new URL(stockUrl);  
	        InputStreamReader isr = new InputStreamReader(url.openStream(),"UTF-8");  
	        BufferedReader br = new BufferedReader(isr);
	        
	        String line = "";
            while ((line = br.readLine()) != null) { 
            	String item [] = line.split(",");
            	
            	result = new Fund();
            	result.setFundPrice(Double.valueOf(item[3]));
            	result.setFundPriceUpDownAmt(Double.valueOf(item[3]) - Double.valueOf(item[2]));
            	result.setFundPriceAmt(Double.valueOf(item[9]));
            	result.setFundPriceVol(Double.valueOf(item[8]));
            	
            	if(Double.valueOf(item[2])>0)
            		result.setFundPriceUpDownPct((Double.valueOf(item[3]) - Double.valueOf(item[2]))/Double.valueOf(item[2]));
            	else 
            		result.setFundPriceUpDownPct(0);
            	
            	//System.out.println(item[3]+line);
            }
		}
        catch (Exception ex)  
        {
        	LoggerManager.getInstance().getLogger().error("Get Fund From Sina Exception: "+stockId);
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        }
		
		return result;
	}
	
	private String generateStockURL(String stockId)
	{
		return TTJJURL.replace(STOCK_CODE_IND, "sz"+stockId);
	}
}
