package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.StockROI;
import utility.log.LoggerManager;
import dao.utility.DBConnectionManager;

public class StockROIDBDAO {
	private Connection conn;
	
	public void getConnection()
	{
		conn = DBConnectionManager.getInstance().getConnection();
	}
	
	public void insertROI(StockROI roi)
	{
		PreparedStatement prestmt = null;
		
		try {
			getConnection();
            prestmt = conn.prepareStatement("INSERT INTO `stock_2_roi` ("
            		+ "`roi_type`, "
            		+ "`stock_id`, "
            		+ "`roi_buy_amt`, "
            		+ "`roi_sell_amt`, "
            		+ "`roi_rest_amt`, "
            		+ "`roi_roi`, "
            		+ "`roi_win_txn_no`, "
            		+ "`roi_lose_txn_no`, "
            		+ "`roi_total_txn_no`, "
            		+ "`roi_sell_txn_no`, "
            		+ "`roi_rest_txn_no`, "
            		+ "`roi_long_txn_no`, "
            		+ "`roi_start_date`, "
            		+ "`roi_end_date` "
            		+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            prestmt.setInt(1, roi.getType());
        	prestmt.setString(2,roi.getStockId());
        	prestmt.setDouble(3,roi.getBuyAmt());
        	prestmt.setDouble(4,roi.getSellAmt());
        	prestmt.setDouble(5,roi.getRestAmt());
        	prestmt.setDouble(6,roi.getRoi());
        	prestmt.setInt(7,roi.getWinTxnNo());
        	prestmt.setInt(8,roi.getLoseTxnNo());
        	prestmt.setInt(9,roi.getTotalTxnNo());
        	prestmt.setInt(10,roi.getSellTxnNo());
        	prestmt.setInt(11,roi.getRestTxnNo());
        	prestmt.setInt(12,roi.getLongTxnNo());
        	prestmt.setDate(13, new java.sql.Date(roi.getStartDate().getTime()));
        	prestmt.setDate(14, new java.sql.Date(roi.getEndDate().getTime()));
        	
        	prestmt.executeUpdate();
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Insert ROI into MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
            if (prestmt != null) {
                try {
                    prestmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Insert ROI into MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                prestmt = null;
            }
        }
	}
	
	public ArrayList<StockROI> getStockROI()
	{
		ArrayList<StockROI> result = new ArrayList<StockROI>();
		StockROI roi = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_roi");
            
            while(rs.next())
            {
            	roi = new StockROI();
            	roi.setDbId(rs.getInt("id"));
            	roi.setType(rs.getInt("roi_type"));
            	roi.setStockId(rs.getString("stock_id"));
            	roi.setBuyAmt(rs.getDouble("roi_buy_amt"));
            	roi.setSellAmt(rs.getDouble("roi_sell_amt"));
            	roi.setRestAmt(rs.getDouble("roi_rest_amt"));
            	roi.setRoi(rs.getDouble("roi_roi"));
            	roi.setWinTxnNo(rs.getInt("roi_win_txn_no"));
            	roi.setLoseTxnNo(rs.getInt("roi_lose_txn_no"));
            	roi.setTotalTxnNo(rs.getInt("roi_total_txn_no"));
            	roi.setSellTxnNo(rs.getInt("roi_sell_txn_no"));
            	roi.setRestTxnNo(rs.getInt("roi_rest_txn_no"));
            	roi.setLongTxnNo(rs.getInt("roi_long_txn_no"));
            	roi.setStartDate(new java.util.Date(rs.getDate("roi_start_date").getTime()));
            	roi.setEndDate(new java.util.Date(rs.getDate("roi_end_date").getTime()));
            	
            	result.add(roi);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get All Stock Data from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get All Stock Data from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get All Stock Data from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public void removeAllStockROI()
	{
		Statement stmt = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            stmt.execute("delete from stock_2_roi where id<99999999999");
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Remove All Stock ROI from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Remove All Stock ROI from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
	}
}
