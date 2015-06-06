package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;

import model.Capital;
import utility.date.DateUtil;
import utility.log.LoggerManager;

import com.csvreader.CsvReader;

public class CapitalCSVDAO {
	private static final String CAPITL_FILE_PATH = "//Users//wangpan//Documents//Projects//Stock//capital.csv";
	private CsvReader csvReader;
	
	private void getCsvReader()
	{
		try {
			csvReader = new CsvReader(CAPITL_FILE_PATH, ',' ,Charset.forName("GBK"));
		} catch (FileNotFoundException e) {
			LoggerManager.getInstance().getLogger().error("Capitabl CSV Reader Exception");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
	}
	
	public ArrayList <Capital> getCapitals()
	{
		ArrayList <Capital> result = new ArrayList <Capital>();;
		Capital capital = null;
		
		// load data from CSV file
		getCsvReader();
		
		// iterate data into bean
		try {
			csvReader.readHeaders();
			
			while(csvReader.readRecord())
			{
				capital = new Capital();
				
				capital.setStockId(csvReader.get("stock_id"));
				capital.setCapitalDate(DateUtil.parse(csvReader.get("capital_date"),"yyyy-MM-dd HH:mm:ss"));
				capital.setStockTotalVolume(Double.parseDouble(csvReader.get("stock_total_volume")));
				capital.setStockAIssueVolume(Double.parseDouble(csvReader.get("stock_aissue_volume")));
				
				result.add(capital);
			}
			
			csvReader.close();
		} catch (IOException e) {
			LoggerManager.getInstance().getLogger().error("Capitabl CSV Reader Exception - IO Exception");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		} catch (ParseException e) {
			LoggerManager.getInstance().getLogger().error("Capitabl CSV Reader Exception - Date Exception");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
		
		return result;
	}
}
