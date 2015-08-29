package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import model.Stock3Data;
import utility.date.DateUtil;
import utility.log.LoggerManager;

public class Stock3DataTecentDAO {
	private static final String TTJJURL = "http://qt.gtimg.cn/q=[STOCKCODE]";
	private static final String STOCK_CODE_IND = "[STOCKCODE]";
	private static final int HUNDRED = 100;
	private static final double TEN_THOUSAND = 10000;
	private static final double HUNDRED_MILLION = 100000000;
	
	public Stock3Data getStockData(String stockId)
	{
		Stock3Data result = null;
		
		try
		{
			String stockUrl = generateStockURL(stockId);
			
			URL url = new URL(stockUrl);  
	        InputStreamReader isr = new InputStreamReader(url.openStream(),"gbk");  
	        BufferedReader br = new BufferedReader(isr);
	        
	        String line = "";
            while ((line = br.readLine()) != null) { 
            	String item [] = line.split("~");
            	
            	if(item.length>1)
            	{
            		result = new Stock3Data();
                	
                	result.setName(item[1]);
                	result.setId(item[2]);
                	result.setPrice_now(Double.valueOf(item[3]));
                	result.setPrice_close_ystd(Double.valueOf(item[4]));
                	result.setPrice_open(Double.valueOf(item[5]));
                	result.setVolume(Integer.valueOf(item[6])*HUNDRED);
                	result.setBuy_vol(Integer.valueOf(item[7])*HUNDRED);
                	result.setSell_vol(Integer.valueOf(item[8])*HUNDRED);
                	result.setBuy_1_prc(Double.valueOf(item[9]));
                	result.setBuy_1_vol(Integer.valueOf(item[10])*HUNDRED);
                	result.setBuy_2_prc(Double.valueOf(item[11]));
                	result.setBuy_2_vol(Integer.valueOf(item[12])*HUNDRED);
                	result.setBuy_3_prc(Double.valueOf(item[13]));
                	result.setBuy_3_vol(Integer.valueOf(item[14])*HUNDRED);
                	result.setBuy_4_prc(Double.valueOf(item[15]));
                	result.setBuy_4_vol(Integer.valueOf(item[16])*HUNDRED);
                	result.setBuy_5_prc(Double.valueOf(item[17]));
                	result.setBuy_5_vol(Integer.valueOf(item[18])*HUNDRED);
                	result.setSell_1_prc(Double.valueOf(item[19]));
                	result.setSell_1_vol(Integer.valueOf(item[20])*HUNDRED);
                	result.setSell_2_prc(Double.valueOf(item[21]));
                	result.setSell_2_vol(Integer.valueOf(item[22])*HUNDRED);
                	result.setSell_3_prc(Double.valueOf(item[23]));
                	result.setSell_3_vol(Integer.valueOf(item[24])*HUNDRED);
                	result.setSell_4_prc(Double.valueOf(item[25]));
                	result.setSell_4_vol(Integer.valueOf(item[26])*HUNDRED);
                	result.setSell_5_prc(Double.valueOf(item[27]));
                	result.setSell_5_vol(Integer.valueOf(item[28])*HUNDRED);
                	result.setTime(DateUtil.parse(item[30], "yyyyMMddhhmmss"));
                	result.setUp_down_prc((item[31].equals(""))?0:Double.valueOf(item[31]));
                	result.setUp_down_pct((item[32].equals(""))?0:Double.valueOf(item[32])/HUNDRED);
                	result.setPrice_high((item[33].equals(""))?0:Double.valueOf(item[33]));
                	result.setPrice_low((item[34].equals(""))?0:Double.valueOf(item[34]));
                	result.setAmount((item[37].equals(""))?0:Double.valueOf(item[37])*TEN_THOUSAND);
                	result.setTurnover((item[38].equals(""))?0:Double.valueOf(item[38])/HUNDRED);
                	result.setPe((item[39].equals(""))?0:Double.valueOf(item[39]));
                	result.setVariation((item[43].equals(""))?0:Double.valueOf(item[43])/HUNDRED);
                	result.setIssued_amt((item[44].equals(""))?0:Double.valueOf(item[44])*HUNDRED_MILLION);
                	result.setTotal_amt((item[45].equals(""))?0:Double.valueOf(item[45])*HUNDRED_MILLION);
                	result.setPb((item[46].equals(""))?0:Double.valueOf(item[46]));
                	result.setPrice_top((item[47].equals(""))?0:Double.valueOf(item[47]));
                	result.setPrice_bottom((item[48].equals(""))?0:Double.valueOf(item[48]));	
            	}
            }
		}
        catch (Exception ex)  
        {
        	LoggerManager.getInstance().getLogger().error("Get Stock 3 Data From Tecent Exception: "+stockId);
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        }
		
		return result;
	}
	
	private String generateStockURL(String stockId)
	{
		return TTJJURL.replace(STOCK_CODE_IND, stockId);
	}
}
