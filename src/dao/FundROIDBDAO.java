package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.FundROI;
import utility.log.LoggerManager;
import dao.utility.DBConnectionManager;

public class FundROIDBDAO {
	private Connection conn;
	
	public void getConnection()
	{
		conn = DBConnectionManager.getInstance().getConnection();
	}
	
	public void insertFundROI(FundROI fundRoi)
	{
		PreparedStatement prestmt = null;
		
		try {
			getConnection();
            prestmt = conn.prepareStatement("INSERT INTO `stock_2_fund_lof_roi` ("
            		+ "`roi_rate_upper`, "
            		+ "`roi_rate_lower`, "
            		+ "`roi_total_txn`, "
            		+ "`roi_win_txn`, "
            		+ "`roi_range_txn`, "
            		+ "`roi_type`, "
            		+ "`roi_isUptrend`, "
            		+ "`roi_roi` "
            		+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            
            prestmt.setDouble(1,fundRoi.getRateUpper());
        	prestmt.setDouble(2,fundRoi.getRateLower());
        	prestmt.setInt(3,fundRoi.getTotalTxnNo());
        	prestmt.setInt(4,fundRoi.getWinTxnNo());
        	prestmt.setInt(5,fundRoi.getDealTxnNo());
        	prestmt.setString(6,fundRoi.getType());
        	prestmt.setBoolean(7,fundRoi.isUptrend());
        	prestmt.setDouble(8,fundRoi.getRoi());
        	
        	prestmt.executeUpdate();
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Insert Fund ROI into MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
            if (prestmt != null) {
                try {
                    prestmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Insert Fund ROI into MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                prestmt = null;
            }
        }
	}
	
	public void removeAllFundROI()
	{
		Statement stmt = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            stmt.execute("delete from stock_2_fund_lof_roi where id<99999999999");
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Remove All Fund ROI from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Remove All Fund ROI from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
	}
	
	public ArrayList<FundROI> getFundROIList()
	{
		ArrayList<FundROI> result = new ArrayList<FundROI>();
		FundROI fundRoi = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from stock_2_fund_lof_roi");
            
			while(rs.next())
            {
            	fundRoi = new FundROI();
            	fundRoi.setDbId(rs.getInt("id"));
            	fundRoi.setRateUpper(rs.getDouble("roi_rate_upper"));
            	fundRoi.setRateLower(rs.getDouble("roi_rate_lower"));
            	fundRoi.setWinTxnNo(rs.getInt("roi_win_txn"));
            	fundRoi.setDealTxnNo(rs.getInt("roi_range_txn"));
            	fundRoi.setTotalTxnNo(rs.getInt("roi_total_txn"));
            	fundRoi.setType(rs.getString("roi_type"));
            	fundRoi.setUptrend(rs.getBoolean("roi_isUptrend"));
            	fundRoi.setRoi(rs.getDouble("roi_roi"));
            	result.add(fundRoi);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Latest Finalized Fund Data from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Latest Finalized Fund Data from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Latest Finalized Fund Data from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
}
