package dao;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;

import com.csvreader.CsvReader;

import utility.date.DateUtil;
import utility.log.LoggerManager;
import model.Capital;

public class CapitalFTPDAO {
	private static final String CAPITL_FILE_PATH = "ftp://pstrike:pstt20150408e@down.licai668.cn/capital/";
	private static final String CAPITL_FILE_NAME = "tb_finance_capital_";
	private static final String CAPITL_FILE_SUFFIX = ".csv";
	
	public ArrayList <Capital> getCapitalsAtDate (Date date)
	{
		ArrayList <Capital> result = new ArrayList <Capital>();
		Capital capital = null;
		
		try {
			URL dataUrl = new URL(getDataURL(date)); 
			InputStream in = dataUrl.openStream(); 
			
			CsvReader csvReader = new CsvReader(in, ',' ,Charset.forName("GBK"));
			
			csvReader.readHeaders();
			
			while(csvReader.readRecord())
			{
				capital = new Capital();
				
				capital.setStockId(csvReader.get("证券代码"));
				capital.setCapitalDate(DateUtil.parse(csvReader.get("时间"),"yyyy-MM-dd HH:mm:ss"));
				capital.setStockTotalVolume(Double.parseDouble(csvReader.get("股份总数")));
				capital.setStockAIssueVolume(Double.parseDouble(csvReader.get("A股")));
				
				result.add(capital);
			}
			
			csvReader.close();
			in.close();
		} catch (Exception e) {
			LoggerManager.getInstance().getLogger().error("FTP Capitabl CSV Reader Exception:"+DateUtil.format(date, "yyyyMMdd"));
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
		
		return result;
	}
	
	private String getDataURL(Date date)
	{
		return CAPITL_FILE_PATH+CAPITL_FILE_NAME+DateUtil.format(date, "yyyyMMdd")+CAPITL_FILE_SUFFIX;
	}
}
