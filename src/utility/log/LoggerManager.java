package utility.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerManager {
	private Logger logger; 
	
	private static final LoggerManager LoggerManager = new LoggerManager();
	public static LoggerManager getInstance(){return LoggerManager;}
	
	private LoggerManager()
	{
		logger = Logger.getLogger("stock_date");
		PropertyConfigurator.configure (System.getProperty("user.dir")+"/config/log4j");
	}
	
	public Logger getLogger()
	{
		return logger;
	}
}