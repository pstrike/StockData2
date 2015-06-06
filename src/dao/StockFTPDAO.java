package dao;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;

import model.Stock;
import utility.date.DateUtil;
import utility.log.LoggerManager;

import com.csvreader.CsvReader;

public class StockFTPDAO {
	private static final String STOCK_FILE_PATH = "ftp://pstrike:pstt20150408e@down.licai668.cn/day/";
	private static final String STOCK_SH_FILE_NAME = "tb_day_sh_";
	private static final String STOCK_SZ_FILE_NAME = "tb_day_sz_";
	private static final String STOCK_FILE_SUFFIX = ".csv";
	public static final String SH_STOCK = "sh";
	public static final String SZ_STOCK = "sz";
	
	public ArrayList <Stock> getStockAtDate (Date date, String type)
	{
		ArrayList <Stock> result = new ArrayList <Stock>();
		Stock stock = null;
		
		try {
			URL dataUrl = new URL(getDataURL(date,type)); 
			InputStream in = dataUrl.openStream(); 
			
			CsvReader csvReader = new CsvReader(in, ',' ,Charset.forName("GBK"));
			
			csvReader.readHeaders();
			
			while(csvReader.readRecord())
			{
				stock = new Stock();
				
				stock.setStockId(csvReader.get("市场代码")+csvReader.get("证券代码"));
				stock.setStockDate(DateUtil.parse(csvReader.get("日期"),"yyyy-MM-dd HH:mm:ss"));
				stock.setStockOpen(Double.parseDouble(csvReader.get("开盘价")));
				stock.setStockHigh(Double.parseDouble(csvReader.get("最高价")));
				stock.setStockLow(Double.parseDouble(csvReader.get("最低价")));
				stock.setStockClose(Double.parseDouble(csvReader.get("收盘价")));
				stock.setStockVol(Double.parseDouble(csvReader.get("成交量"))*100);
				stock.setStockAmt(Double.parseDouble(csvReader.get("成交金额")));
				result.add(stock);
			}
			
			csvReader.close();
			in.close();
		} catch (Exception e) {
			LoggerManager.getInstance().getLogger().error("FTP Stock CSV Reader Exception:"+DateUtil.format(date, "yyyyMMdd")+";"+type);
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
		
		return result;
	}
	
	private String getDataURL(Date date, String type)
	{
		if(type.equals(SH_STOCK))
			return STOCK_FILE_PATH+STOCK_SH_FILE_NAME+DateUtil.format(date, "yyyyMMdd")+STOCK_FILE_SUFFIX;
		else if (type.equals(SZ_STOCK))
			return STOCK_FILE_PATH+STOCK_SZ_FILE_NAME+DateUtil.format(date, "yyyyMMdd")+STOCK_FILE_SUFFIX;
		else
			return null;
	}
}
