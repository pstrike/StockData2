package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;

import model.Stock;
import utility.date.DateUtil;
import utility.log.LoggerManager;

import com.csvreader.CsvReader;

public class StockCSVDAO {
	public static final String SH_STOCK = "sh";
	public static final String SZ_STOCK = "sz";
	//private static final String STOCK_SH_FILE_PATH = "//Users//wangpan//Documents//Projects//Stock//tb_day_sh.csv";
	//private static final String STOCK_SZ_FILE_PATH = "//Users//wangpan//Documents//Projects//Stock//tb_day_sz.csv";
	private static final String STOCK_SH_FILE_PATH = "D://StockData2//data//tb_day_sh.csv";
	private static final String STOCK_SZ_FILE_PATH = "D://StockData2//data//tb_day_sz.csv";
	private CsvReader csvReader;
	
	private void getCsvReader(String filePath)
	{
		try {
			csvReader = new CsvReader(filePath, ',' ,Charset.forName("GBK"));
		} catch (FileNotFoundException e) {
			LoggerManager.getInstance().getLogger().error("Capitabl CSV Reader Exception");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
	}
	
	public ArrayList <Stock> getStocks(int begin, int end, String type)
	{
		ArrayList <Stock> result = new ArrayList <Stock>();
		Stock stock = null;
		int counter = 1;
		
		// load data from CSV file base on stock type
		if(type.equals(SH_STOCK))
		{
			getCsvReader(STOCK_SH_FILE_PATH);
		}
		else if(type.equals(SZ_STOCK))
		{
			getCsvReader(STOCK_SZ_FILE_PATH);
		}
		else
		{
			return null;
		}
		
		// iterate data into bean
		try {
			csvReader.readHeaders();
			
			while(csvReader.readRecord())
			{
				if(counter>=begin && counter<=end)
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
				counter++;
			}
			
			csvReader.close();
		} catch (IOException e) {
			LoggerManager.getInstance().getLogger().error("Stock CSV Reader Exception - IO Exception");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		} catch (ParseException e) {
			LoggerManager.getInstance().getLogger().error("Stock CSV Reader Exception - Date Exception");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
		
		return result;
	}
}
