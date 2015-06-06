package dao.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utility.log.LoggerManager;

public class DBConnectionManager {

	private Connection conn;	
	
	private DBConnectionManager()
	{

	}   
	
    private static final DBConnectionManager connMgr = new DBConnectionManager();    
    public static DBConnectionManager getInstance(){return connMgr;}  
	
	public Connection getConnection()
	{
		try {
			if(conn == null || !conn.isValid(0))
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
		        
		        conn = DriverManager.getConnection(DBInfo.DB_URL);
		        
		        LoggerManager.getInstance().getLogger().info("DB Connection created");
			}
		 }
		 catch (Exception e) {
			 LoggerManager.getInstance().getLogger().error("Create DB Connection Err");
			 LoggerManager.getInstance().getLogger().error(e);
			 e.printStackTrace();
		 }	
		 return conn;
	}
	
	public void closeConnection()
	{
		try {
			if(conn != null)
			{
	            conn.close();
	            LoggerManager.getInstance().getLogger().info("DB Connection Closed");
			}
        }
		catch (SQLException sqlEx) {
        	LoggerManager.getInstance().getLogger().error("Close DB Connection Err");
        	LoggerManager.getInstance().getLogger().error(sqlEx);
        }

        conn = null;
	}
	
}