package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.Stock3Data;
import utility.log.LoggerManager;
import dao.utility.DBConnectionManager;

public class Stock3DataDBDAO {
	
	private Connection conn;
	
	public void getConnection()
	{
		conn = DBConnectionManager.getInstance().getConnection();
	}

	public void removeAllStockData()
	{
		Statement stmt = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            stmt.execute("delete from stock_3_data where id<999999999999");
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Remove All Stock 3 Data from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Remove All Stock 3 Data from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
	}
	
	public void insertStockData(Stock3Data stockData)
	{
		PreparedStatement prestmt = null;
		
		try {
			getConnection();
            prestmt = conn.prepareStatement("INSERT INTO stock_3_data ("
            		+ "name, "
            		+ "id, "
            		+ "price_now, "
            		+ "price_close_ystd, "
            		+ "price_open, "
            		+ "volume, "
            		+ "buy_vol, "
            		+ "sell_vol, "
            		+ "buy_1_prc, "
            		+ "buy_1_vol, "
            		+ "buy_2_prc, "
            		+ "buy_2_vol, "
            		+ "buy_3_prc, "
            		+ "buy_3_vol, "
            		+ "buy_4_prc, "
            		+ "buy_4_vol, "
            		+ "buy_5_prc, "
            		+ "buy_5_vol, "
            		+ "sell_1_prc, "
            		+ "sell_1_vol, "
            		+ "sell_2_prc, "
            		+ "sell_2_vol, "
            		+ "sell_3_prc, "
            		+ "sell_3_vol, "
            		+ "sell_4_prc, "
            		+ "sell_4_vol, "
            		+ "sell_5_prc, "
            		+ "sell_5_vol, "
            		+ "time, "
            		+ "up_down_prc, "
            		+ "up_down_pct, "
            		+ "price_high, "
            		+ "price_low, "
            		+ "amount, "
            		+ "turnover, "
            		+ "pe, "
            		+ "variation, "
            		+ "issued_amt, "
            		+ "total_amt, "
            		+ "pb, "
            		+ "price_top, "
            		+ "price_bottom "
            		+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            prestmt.setString(1,stockData.getName());
        	prestmt.setString(2,stockData.getId());
        	prestmt.setDouble(3,stockData.getPrice_now());
        	prestmt.setDouble(4,stockData.getPrice_close_ystd());
        	prestmt.setDouble(5,stockData.getPrice_open());
        	prestmt.setInt(6,stockData.getVolume());
        	prestmt.setInt(7,stockData.getBuy_vol());
        	prestmt.setInt(8,stockData.getSell_vol());
        	prestmt.setDouble(9,stockData.getBuy_1_prc());
        	prestmt.setInt(10,stockData.getBuy_1_vol());
        	prestmt.setDouble(11,stockData.getBuy_2_prc());
        	prestmt.setInt(12,stockData.getBuy_2_vol());
        	prestmt.setDouble(13,stockData.getBuy_3_prc());
        	prestmt.setInt(14,stockData.getBuy_3_vol());
        	prestmt.setDouble(15,stockData.getBuy_4_prc());
        	prestmt.setInt(16,stockData.getBuy_4_vol());
        	prestmt.setDouble(17,stockData.getBuy_5_prc());
        	prestmt.setInt(18,stockData.getBuy_5_vol());
        	prestmt.setDouble(19,stockData.getSell_1_prc());
        	prestmt.setInt(20,stockData.getSell_1_vol());
        	prestmt.setDouble(21,stockData.getSell_2_prc());
        	prestmt.setInt(22,stockData.getSell_2_vol());
        	prestmt.setDouble(23,stockData.getSell_3_prc());
        	prestmt.setInt(24,stockData.getSell_3_vol());
        	prestmt.setDouble(25,stockData.getSell_4_prc());
        	prestmt.setInt(26,stockData.getSell_4_vol());
        	prestmt.setDouble(27,stockData.getSell_5_prc());
        	prestmt.setInt(28,stockData.getSell_5_vol());
        	prestmt.setTimestamp(29,new java.sql.Timestamp(stockData.getTime().getTime()));
        	prestmt.setDouble(30,stockData.getUp_down_prc());
        	prestmt.setDouble(31,stockData.getUp_down_pct());
        	prestmt.setDouble(32,stockData.getPrice_high());
        	prestmt.setDouble(33,stockData.getPrice_low());
        	prestmt.setDouble(34,stockData.getAmount());
        	prestmt.setDouble(35,stockData.getTurnover());
        	prestmt.setDouble(36,stockData.getPe());
        	prestmt.setDouble(37,stockData.getVariation());
        	prestmt.setDouble(38,stockData.getIssued_amt());
        	prestmt.setDouble(39,stockData.getTotal_amt());
        	prestmt.setDouble(40,stockData.getPb());
        	prestmt.setDouble(41,stockData.getPrice_top());
        	prestmt.setDouble(42,stockData.getPrice_bottom());
        	
        	prestmt.executeUpdate();
            
        }
		catch (MySQLIntegrityConstraintViolationException ex) {
        	LoggerManager.getInstance().getLogger().info("Duplicated Record: "+stockData.getId());
        } 
		catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Insert Stock 3 Data into MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
            if (prestmt != null) {
                try {
                    prestmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Insert Stock 3 Data into MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                prestmt = null;
            }
        }
	}
	
}
