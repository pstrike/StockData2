package runner;

import java.text.ParseException;
import java.util.Date;

import utility.date.DateUtil;
import utility.log.LoggerManager;
import autodeal.Monitor;
import dao.utility.DBConnectionManager;

public class AutoDealBuyRunner {
	
	public static void main(String[] args) {
		
		Date todayDatetime = new Date();
		
		try {
			if(DateUtil.getHour(todayDatetime) >= 10 && DateUtil.getHour(todayDatetime) < 15)
			{
				LoggerManager.getInstance().getLogger().info("Auto Deal Buy Processing Begin");
				
				Monitor monitor = new Monitor();
				monitor.buyMonitor();
				
				LoggerManager.getInstance().getLogger().info("Auto Deal Buy Data Processing End");
			}
		} catch (ParseException e) {
			LoggerManager.getInstance().getLogger().error("Auto Deal Buy Err");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();
	}

}
