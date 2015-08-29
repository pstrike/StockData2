package runner;

import java.text.ParseException;
import java.util.Date;

import utility.date.DateUtil;
import utility.log.LoggerManager;
import autodeal.Monitor;
import dao.utility.DBConnectionManager;

public class AutoDealSellRunner {

	public static void main(String[] args) {
		Date todayDatetime = new Date();
		
		try {
			if(DateUtil.getHour(todayDatetime) >= 14 && DateUtil.getHour(todayDatetime) < 15)
			{
				LoggerManager.getInstance().getLogger().info("Auto Deal Sell Processing Begin");
				
				Monitor monitor = new Monitor();
				monitor.sellMonitor();
				
				LoggerManager.getInstance().getLogger().info("Auto Deal Sell Data Processing End");
			}
		} catch (ParseException e) {
			LoggerManager.getInstance().getLogger().error("Auto Deal Buy Sell");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();
	}
	
}
