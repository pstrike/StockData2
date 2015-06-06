package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import utility.date.DateUtil;
import utility.log.LoggerManager;
import model.Capital;
import dao.utility.DBConnectionManager;

public class CapitalDBDAO {

	private Connection conn;
	
	public void getConnection()
	{
		conn = DBConnectionManager.getInstance().getConnection();
	}
	
	public void insertCapital(Capital capital)
	{
		PreparedStatement prestmt = null;
		
		try {
			getConnection();
            prestmt = conn.prepareStatement("INSERT INTO `stock_2_capital` ("
            		+ "stock_id, "
            		+ "capital_date, "
            		+ "stock_total_volume, "
            		+ "stock_aissue_volume"
            		+ ") VALUES (?, ?, ?, ?)");
            
            prestmt.setString(1,capital.getStockId());
            prestmt.setDate(2,new java.sql.Date(capital.getCapitalDate().getTime()));
            prestmt.setDouble(3,capital.getStockTotalVolume());
            prestmt.setDouble(4,capital.getStockAIssueVolume());
        	
        	prestmt.executeUpdate();
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Insert Capital into MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
            if (prestmt != null) {
                try {
                    prestmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Insert Capital into MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                prestmt = null;
            }
        }
	}
	
	public Date getCapitalLatestDate()
	{
		Date result = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select distinct max(capital_date) from stock_2_capital");
            
            rs.next();
            
            result = new java.util.Date(rs.getDate("max(capital_date)").getTime());
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Latest Capital from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Latest Capital from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                rs = null;
            }
        	
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Latest Capital from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public boolean isExistCapital (Capital capital)
	{
		boolean result = false;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select count(*) from stock_2_capital where "
            		+ "capital_date='"+DateUtil.format(capital.getCapitalDate())+"'"
            		+ " and stock_id='"+capital.getStockId()+"'");
            
            rs.next();
            
            if(rs.getInt("count(*)")>0)
            {
            	result = true;
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Check Existence of Capital from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Check Existence of Capital from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                rs = null;
            }
        	
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Latest Capital from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public Capital getCapitalAtDate(String stockId, Date date)
	{
		Capital result = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_capital where "
            		+ "capital_date <= '"+DateUtil.format(date)+"'"
            		+ " and stock_id='"+stockId+"'"
            		+ " order by capital_date desc");
            
            rs.next();
            
            result = new Capital();
            result.setDbId(rs.getInt("id"));
            result.setStockId(rs.getString("stock_id"));
            result.setCapitalDate(rs.getDate("capital_date"));
            result.setStockTotalVolume(rs.getDouble("stock_total_volume"));
            result.setStockAIssueVolume(rs.getDouble("stock_aissue_volume"));
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Latest Capital from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Latest Capital from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                rs = null;
            }
        	
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Latest Capital from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
}
