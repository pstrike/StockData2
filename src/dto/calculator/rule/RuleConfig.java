package dto.calculator.rule;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

import utility.date.DateUtil;
import utility.log.LoggerManager;

public class RuleConfig {
	public final static String RULE_CONFIG_FILE_PATH = "/config/CalculationConfig";

	private Properties prop;
	private static final RuleConfig ruleConfig = new RuleConfig();
	public static RuleConfig getInstance(){return ruleConfig;}
	
	private void loadProperties()
	{	
		try {
			if(prop == null)
			{
				InputStream in = new BufferedInputStream(new FileInputStream(System.getProperty("user.dir")+RULE_CONFIG_FILE_PATH));
				prop = new Properties();
				prop.load(in);
			}
			
		} catch (Exception e) {
			LoggerManager.getInstance().getLogger().error("Load Rule Config Err");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
	}
	
	public double getLowToBuyUpper()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("lowtobuyupper"));
		
		return result;
	}
	
	public double getLowToBuyLower()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("lowtobuylower"));
		
		return result;
	}
	
	public double getLowestMovingAvgVar()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("lowestmovingavgvar"));
		
		return result;
	}
	
	public double getLowestVarRate()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("lowestvarrate"));
		
		return result;
	}
	
	public int getMovingAvgVarBackTo()
	{
		int result = 0;
		
		loadProperties();
		
		result = Integer.parseInt(prop.getProperty("movingavgvarbackto"));
		
		return result;
	}
	
	public double getGoodToSell()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("goodtosell"));
		
		return result;
	}
	
	public double getBadToSell()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("badtosell"));
		
		return result;
	}
	
	public double getBuyAmt()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("buyamt"));
		
		return result;
	}
	
	public double getBuyVol()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("buyvol"));
		
		return result;
	}
	
	public double getVarMovingVarDistance()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("varmovingvardistance"));
		
		return result;
	}
	
	public double getTrendingPct()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("trendingpct"));
		
		return result;
	}
	
	public int getLongToSellDay()
	{
		int result = 0;
		
		loadProperties();
		
		result = Integer.parseInt(prop.getProperty("longtosellday"));
		
		return result;
	}
	
	public double getLongToSell()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("longtosell"));
		
		return result;
	}
	
	public double getAdjustCostRate()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("adjustcostrate"));
		
		return result;
	}
	
	public double getAdjustCostHigHestRate()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("adjustcosthighestrate"));
		
		return result;
	}
	
	public Date getBuyAfterDate()
	{
		Date result = null;
		
		loadProperties();
		
		String dateString = prop.getProperty("buyafterdate");
		
		try {
			result = DateUtil.parse(dateString);
		} catch (ParseException e) {
			LoggerManager.getInstance().getLogger().error("Get Buy After Date From Rule Config Err");
			LoggerManager.getInstance().getLogger().error(e);
		}
		
		return result;
	}
	
	public int getMACDFastEMA()
	{
		int result = 0;
		
		loadProperties();
		
		result = Integer.parseInt(prop.getProperty("macdfastema"));
		
		return result;
	}
	
	public int getMACDSlowEMA()
	{
		int result = 0;
		
		loadProperties();
		
		result = Integer.parseInt(prop.getProperty("macdslowema"));
		
		return result;
	}
	
	public int getMACDDEAEMA()
	{
		int result = 0;
		
		loadProperties();
		
		result = Integer.parseInt(prop.getProperty("macddeaema"));
		
		return result;
	}
	
	public double getBuyRuleLowestMACDDIF()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("buyrulelowestmacddif"));
		
		return result;
	}
	
	public double getBuyRuleLowestMACD()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("buyrulelowestmacd"));
		
		return result;
	}
	
	public double getBuyRuleHighestMACDDIFRate()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("buyrulehigheastmacddifrate"));
		
		return result;
	}
	
	public double getBuyRuleTrendingFlatRate()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("buyruletrendingflagrate"));
		
		return result;
	}
	
	public double getBollDay()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("bollday"));
		
		return result;
	}
	
	public double getLowDevRate()
	{
		double result = 0;
		
		loadProperties();
		
		result = Double.parseDouble(prop.getProperty("lowdevrate"));
		
		return result;
	}
}
