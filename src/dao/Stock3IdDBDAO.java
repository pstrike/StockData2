package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Stock3Id;
import utility.log.LoggerManager;
import dao.utility.DBConnectionManager;

public class Stock3IdDBDAO {
	private Connection conn;
	
	public void getConnection()
	{
		conn = DBConnectionManager.getInstance().getConnection();
	}
	
	public void removeAllStockId()
	{
		Statement stmt = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            stmt.execute("delete from stock_3_id where id<99999999999");
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Remove All Stock 3 ID from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Remove All Stock 3 ID from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
	}
	
	public void insertStockId(Stock3Id stockId)
	{
		PreparedStatement prestmt = null;
		
		try {
			getConnection();
            prestmt = conn.prepareStatement("INSERT INTO stock_3_id ("
            		+ "name, "
            		+ "id, "
            		+ "market, "
            		+ "category, "
            		+ "type "
            		+ ") VALUES (?, ?, ?, ?, ?)");
            
            prestmt.setString(1,stockId.getName());
        	prestmt.setString(2,stockId.getId());
        	prestmt.setString(3,stockId.getMarket());
        	prestmt.setString(4,stockId.getCategory());
        	prestmt.setString(5,stockId.getType());
        	
        	prestmt.executeUpdate();
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Insert Stock 3 ID into MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
            if (prestmt != null) {
                try {
                    prestmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Insert Stock 3 ID into MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                prestmt = null;
            }
        }
	}
	
	public ArrayList<Stock3Id> getAllStockId()
	{
		ArrayList<Stock3Id> result = new ArrayList<Stock3Id>();
		Stock3Id stockId = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_3_id");
            
            while(rs.next())
            {
            	stockId = new Stock3Id();
            	stockId.setDbId(rs.getInt("db_id"));
            	stockId.setName(rs.getString("name"));
            	stockId.setId(rs.getString("id"));
            	stockId.setMarket(rs.getString("market"));
            	stockId.setCategory(rs.getString("category"));
            	stockId.setType(rs.getString("type"));
            	
            	result.add(stockId);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Stock 3 Id from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Stock 3 Id from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Stock 3 Id from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}

}
