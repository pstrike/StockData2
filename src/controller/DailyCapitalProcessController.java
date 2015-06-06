package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Capital;
import utility.date.DateUtil;
import utility.log.LoggerManager;
import dao.CapitalDBDAO;
import dao.CapitalFTPDAO;

public class DailyCapitalProcessController extends Controller{

	@Override
	public void action()
	{
		// initialization
		CapitalFTPDAO capitalFtpDao = new CapitalFTPDAO();
		CapitalDBDAO capitalDbDao = new CapitalDBDAO();
		ArrayList <Date> toProcessDates = new ArrayList<Date>();
		Date toProcessDate = null;
		int counter = 0;
		
		try {
			// figure out to be processed date
			Date capitalDate = capitalDbDao.getCapitalLatestDate();
			Date endDate = DateUtil.addDay(DateUtil.parse(DateUtil.getToday()),1);
			
			while(capitalDate.before(endDate))
			{
				toProcessDates.add(capitalDate);
				capitalDate = DateUtil.addDay(capitalDate, 1);
			}
			
			// begin ETL base on to be processed date
			counter = toProcessDates.size(); // counter is used as progress indicator
			while(toProcessDates.size()>0)
			{
				toProcessDate = toProcessDates.get(0);
				
				// extraction
				ArrayList<Capital> capitals = capitalFtpDao.getCapitalsAtDate(toProcessDate);
				
				for(Capital capital : capitals)
				{
					if(!capitalDbDao.isExistCapital(capital))
					{
						// load data into DB
						capitalDbDao.insertCapital(capital);
						LoggerManager.getInstance().getLogger().info("Daily Capital Processing:"+DateUtil.format(capital.getCapitalDate())+":"+capital.getStockId()+" completed");
					}
				}
				
				counter--;
				LoggerManager.getInstance().getLogger().info("Daily Capital Processing:"+DateUtil.format(toProcessDate)+" completed; To be processed date:"+counter);
				toProcessDates.remove(0);
			}
			
		} catch (Exception e) {
			LoggerManager.getInstance().getLogger().error("Daily Capital Processing Exception");
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
	}

}
