package utility.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil  
{  
    private static String defaultDatePattern = "yyyy-MM-dd";  
    private static String TimeDatePattern = "yyyy-MM-dd hh:mm:ss";  
  
    /** 
     * 获得默认的 date pattern 
     */  
    public static String getDatePattern()  
    {  
        return defaultDatePattern;  
    }  
    
    public static String getTimeDatePattern()  
    {  
        return TimeDatePattern;  
    }  
  
    /** 
     * 返回预设Format的当前日期字符串 
     */  
    public static String getToday()  
    {  
        Date today = new Date();  
        return format(today);  
    } 
  
    /** 
     * 使用预设Format格式化Date成字符串 
     */  
    public static String format(Date date)  
    {  
        return date == null ? " " : format(date, getDatePattern());  
    }  
  
    /** 
     * 使用参数Format格式化Date成字符串 
     */  
    public static String format(Date date, String pattern)  
    {  
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);  
    }  
  
    /** 
     * 使用预设格式将字符串转为Date 
     */  
    public static Date parse(String strDate) throws ParseException  
    {
    	if(strDate == null || strDate.equals(""))
    		return null;
    	else
    		return parse(strDate, getDatePattern());  
    }  
  
    /** 
     * 使用参数Format将字符串转为Date 
     */  
    public static Date parse(String strDate, String pattern)  
            throws ParseException  
    {  
    	if(strDate == null || strDate.equals(""))
    		return null;
    	else
    		return new SimpleDateFormat(pattern).parse(strDate);     
    }  
  
    /** 
     * 在日期上增加数个整月 
     */  
    public static Date addMonth(Date date, int n)  
    {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.add(Calendar.MONTH, n);  
        return cal.getTime();  
    }
    
    /** 
     * 在日期上增加数个整天 
     */  
    public static Date addDay(Date date, int n)  
    {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.add(Calendar.DAY_OF_MONTH, n);  
        return cal.getTime();  
    }  
  
    public static String getLastDayOfMonth(String year, String month)  
    {  
        Calendar cal = Calendar.getInstance();  
        // 年  
        cal.set(Calendar.YEAR, Integer.parseInt(year));  
        // 月，因为Calendar里的月是从0开始，所以要-1  
        cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);  
        // 日，设为一号  
        cal.set(Calendar.DATE, 1);  
        // 月份加一，得到下个月的一号  
        cal.add(Calendar.MONTH, 1);  
        // 下一个月减一为本月最后一天  
        cal.add(Calendar.DATE, -1);  
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号  
    }  
  
    public static Date getDate(String year, String month, String day)  
            throws ParseException  
    {  
        String result = year + "-"  
                + (month.length() == 1 ? ("0 " + month) : month) + "-"  
                + (day.length() == 1 ? ("0 " + day) : day);  
        return parse(result);  
    }
    
    public static int getHour(Date date)  
            throws ParseException  
    {  
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    
    public static int getWeekDay(Date date)  
            throws ParseException  
    {  
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK)-1;
    }
    
    public static int dateSubtract(Date newDate, Date oldDate)  
    {  
        int result = 0;
        
        long timeNew=newDate.getTime();
        long timeOld=oldDate.getTime();
        result=new Long((timeNew-timeOld)/(1000*60*60*24)).intValue();
        
        return result;
    } 
}  