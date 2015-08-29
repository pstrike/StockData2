package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Fund;
import utility.log.LoggerManager;
import dao.utility.DBConnectionManager;

public class FundDBDAO
{
	private Connection conn;
	
	public void getConnection()
	{
		conn = DBConnectionManager.getInstance().getConnection();
	}
	
	public ArrayList<Fund> getFundIdList()
	{
		ArrayList<Fund> result = new ArrayList<Fund>();
		
		Statement stmt = null;
		ResultSet rs = null;
		Fund fund = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_fund_lof_list");
            
            while(rs.next())
            {
            	fund = new Fund();
            	fund.setDbId(rs.getInt("id"));
            	fund.setFundId(rs.getString("fund_id"));
            	fund.setFundName(rs.getString("fund_name"));
            	fund.setFundUrl(rs.getString("fund_url"));
            	
            	result.add(fund);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get All Fund LOF List from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get All Fund LOF List from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get All Fund LOF List from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public ArrayList<Fund> getLastestFundList()
	{
		ArrayList<Fund> result = new ArrayList<Fund>();
		Fund fund = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_fund_lof_data where fund_date = (select max(fund_date) from stock_2_fund_lof_data)");
            
            while(rs.next())
            {
            	fund = new Fund();
            	fund.setDbId(rs.getInt("id"));
            	fund.setFundId(rs.getString("fund_id"));
            	fund.setFundName(rs.getString("fund_name"));
            	fund.setFundDate(new java.util.Date(rs.getTimestamp("fund_date").getTime()));
            	fund.setFundPrice(rs.getDouble("fund_price"));
            	fund.setFundValueYesterday(rs.getDouble("fund_value_yesterday"));
            	fund.setFundEstimatedValue(rs.getDouble("fund_estimatedValue"));
            	fund.setFundPriceUpDownAmt(rs.getDouble("fund_price_upDown_amt"));
            	fund.setFundPriceUpDownPct(rs.getDouble("fund_price_upDown_pct"));
            	fund.setFundPriceVol(rs.getDouble("fund_price_vol"));
            	fund.setFundPriceAmt(rs.getDouble("fund_price_amt"));
            	fund.setFundValueUpDownAmt(rs.getDouble("fund_value_upDown_amt"));
            	fund.setFundValueUpDownPct(rs.getDouble("fund_value_upDown_pct"));
            	fund.setFundRate(rs.getDouble("fund_rate"));
            	fund.setFundFinalize(rs.getBoolean("fund_finalize"));
            	fund.setFundAccurateRate(rs.getDouble("fund_accurate_rate"));
            	fund.setFundValueToday(rs.getDouble("fund_value_today"));
            	
            	fund.setFundValueTmr(rs.getDouble("fund_value_tmr"));
            	fund.setFundPriceD0Avg(rs.getDouble("fund_price_d0_avg"));
            	fund.setFundPriceD1Avg(rs.getDouble("fund_price_d1_avg"));
            	fund.setFundPriceD2Avg(rs.getDouble("fund_price_d2_avg"));
            	fund.setFundDealARoi(rs.getDouble("fund_deal_a_roi"));
            	fund.setFundDealBRoi(rs.getDouble("fund_deal_b_roi"));
            	
            	result.add(fund);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Fund Data from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Fund Data from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Fund Data from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public ArrayList<Fund> getAllFundList()
	{
		ArrayList<Fund> result = new ArrayList<Fund>();
		Fund fund = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_fund_lof_data");
            
            while(rs.next())
            {
            	fund = new Fund();
            	fund.setDbId(rs.getInt("id"));
            	fund.setFundId(rs.getString("fund_id"));
            	fund.setFundName(rs.getString("fund_name"));
            	fund.setFundDate(new java.util.Date(rs.getTimestamp("fund_date").getTime()));
            	fund.setFundPrice(rs.getDouble("fund_price"));
            	fund.setFundValueYesterday(rs.getDouble("fund_value_yesterday"));
            	fund.setFundEstimatedValue(rs.getDouble("fund_estimatedValue"));
            	fund.setFundPriceUpDownAmt(rs.getDouble("fund_price_upDown_amt"));
            	fund.setFundPriceUpDownPct(rs.getDouble("fund_price_upDown_pct"));
            	fund.setFundPriceVol(rs.getDouble("fund_price_vol"));
            	fund.setFundPriceAmt(rs.getDouble("fund_price_amt"));
            	fund.setFundValueUpDownAmt(rs.getDouble("fund_value_upDown_amt"));
            	fund.setFundValueUpDownPct(rs.getDouble("fund_value_upDown_pct"));
            	fund.setFundRate(rs.getDouble("fund_rate"));
            	fund.setFundFinalize(rs.getBoolean("fund_finalize"));
            	fund.setFundAccurateRate(rs.getDouble("fund_accurate_rate"));
            	fund.setFundValueToday(rs.getDouble("fund_value_today"));
            	
            	fund.setFundValueTmr(rs.getDouble("fund_value_tmr"));
            	fund.setFundPriceD0Avg(rs.getDouble("fund_price_d0_avg"));
            	fund.setFundPriceD1Avg(rs.getDouble("fund_price_d1_avg"));
            	fund.setFundPriceD2Avg(rs.getDouble("fund_price_d2_avg"));
            	fund.setFundDealARoi(rs.getDouble("fund_deal_a_roi"));
            	fund.setFundDealBRoi(rs.getDouble("fund_deal_b_roi"));
            	
            	result.add(fund);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Fund Data from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Fund Data from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Fund Data from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public ArrayList<Fund> getFinalizeFundListDescBaseOnFundId(String fundId)
	{
		ArrayList<Fund> result = new ArrayList<Fund>();
		Fund fund = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_fund_lof_data where fund_finalize = 1 and fund_id='"+fundId+"' order by fund_date desc");
            
            while(rs.next())
            {
            	fund = new Fund();
            	fund.setDbId(rs.getInt("id"));
            	fund.setFundId(rs.getString("fund_id"));
            	fund.setFundName(rs.getString("fund_name"));
            	fund.setFundDate(new java.util.Date(rs.getTimestamp("fund_date").getTime()));
            	fund.setFundPrice(rs.getDouble("fund_price"));
            	fund.setFundValueYesterday(rs.getDouble("fund_value_yesterday"));
            	fund.setFundEstimatedValue(rs.getDouble("fund_estimatedValue"));
            	fund.setFundPriceUpDownAmt(rs.getDouble("fund_price_upDown_amt"));
            	fund.setFundPriceUpDownPct(rs.getDouble("fund_price_upDown_pct"));
            	fund.setFundPriceVol(rs.getDouble("fund_price_vol"));
            	fund.setFundPriceAmt(rs.getDouble("fund_price_amt"));
            	fund.setFundValueUpDownAmt(rs.getDouble("fund_value_upDown_amt"));
            	fund.setFundValueUpDownPct(rs.getDouble("fund_value_upDown_pct"));
            	fund.setFundRate(rs.getDouble("fund_rate"));
            	fund.setFundFinalize(rs.getBoolean("fund_finalize"));
            	fund.setFundAccurateRate(rs.getDouble("fund_accurate_rate"));
            	fund.setFundValueToday(rs.getDouble("fund_value_today"));
            	
            	fund.setFundValueTmr(rs.getDouble("fund_value_tmr"));
            	fund.setFundPriceD0Avg(rs.getDouble("fund_price_d0_avg"));
            	fund.setFundPriceD1Avg(rs.getDouble("fund_price_d1_avg"));
            	fund.setFundPriceD2Avg(rs.getDouble("fund_price_d2_avg"));
            	fund.setFundDealARoi(rs.getDouble("fund_deal_a_roi"));
            	fund.setFundDealBRoi(rs.getDouble("fund_deal_b_roi"));
            	
            	result.add(fund);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Fund Data from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Fund Data from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Fund Data from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public ArrayList<Fund> getFinalizeFundListAscBaseOnFundId(String fundId)
	{
		ArrayList<Fund> result = new ArrayList<Fund>();
		Fund fund = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_fund_lof_data where fund_finalize = 1 and fund_id='"+fundId+"' order by fund_date asc");
            
            while(rs.next())
            {
            	fund = new Fund();
            	fund.setDbId(rs.getInt("id"));
            	fund.setFundId(rs.getString("fund_id"));
            	fund.setFundName(rs.getString("fund_name"));
            	fund.setFundDate(new java.util.Date(rs.getTimestamp("fund_date").getTime()));
            	fund.setFundPrice(rs.getDouble("fund_price"));
            	fund.setFundValueYesterday(rs.getDouble("fund_value_yesterday"));
            	fund.setFundEstimatedValue(rs.getDouble("fund_estimatedValue"));
            	fund.setFundPriceUpDownAmt(rs.getDouble("fund_price_upDown_amt"));
            	fund.setFundPriceUpDownPct(rs.getDouble("fund_price_upDown_pct"));
            	fund.setFundPriceVol(rs.getDouble("fund_price_vol"));
            	fund.setFundPriceAmt(rs.getDouble("fund_price_amt"));
            	fund.setFundValueUpDownAmt(rs.getDouble("fund_value_upDown_amt"));
            	fund.setFundValueUpDownPct(rs.getDouble("fund_value_upDown_pct"));
            	fund.setFundRate(rs.getDouble("fund_rate"));
            	fund.setFundFinalize(rs.getBoolean("fund_finalize"));
            	fund.setFundAccurateRate(rs.getDouble("fund_accurate_rate"));
            	fund.setFundValueToday(rs.getDouble("fund_value_today"));
            	
            	fund.setFundValueTmr(rs.getDouble("fund_value_tmr"));
            	fund.setFundPriceD0Avg(rs.getDouble("fund_price_d0_avg"));
            	fund.setFundPriceD1Avg(rs.getDouble("fund_price_d1_avg"));
            	fund.setFundPriceD2Avg(rs.getDouble("fund_price_d2_avg"));
            	fund.setFundDealARoi(rs.getDouble("fund_deal_a_roi"));
            	fund.setFundDealBRoi(rs.getDouble("fund_deal_b_roi"));
            	
            	result.add(fund);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Fund Data from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Fund Data from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Fund Data from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public ArrayList<Fund> getLastestFinalizeFundBaseOnFundId(String fundId, int dayNo)
	{
		ArrayList<Fund> result = new ArrayList<Fund>();
		Fund fund = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            /*rs = stmt.executeQuery("select * from stock_2_fund_lof_data where fund_date = (select max(fund_date) from stock_2_fund_lof_data where fund_finalize = 1 and fund_id='"+fundId+"') "
            		+ "and fund_id='"+fundId+"'");*/
			rs = stmt.executeQuery("select * from stock_2_fund_lof_data where fund_finalize = 1 and fund_id='"+fundId+"' order by fund_date desc limit "+dayNo);
            
			while(rs.next())
            {
            	fund = new Fund();
            	fund.setDbId(rs.getInt("id"));
            	fund.setFundId(rs.getString("fund_id"));
            	fund.setFundName(rs.getString("fund_name"));
            	fund.setFundDate(new java.util.Date(rs.getTimestamp("fund_date").getTime()));
            	fund.setFundPrice(rs.getDouble("fund_price"));
            	fund.setFundValueYesterday(rs.getDouble("fund_value_yesterday"));
            	fund.setFundEstimatedValue(rs.getDouble("fund_estimatedValue"));
            	fund.setFundPriceUpDownAmt(rs.getDouble("fund_price_upDown_amt"));
            	fund.setFundPriceUpDownPct(rs.getDouble("fund_price_upDown_pct"));
            	fund.setFundPriceVol(rs.getDouble("fund_price_vol"));
            	fund.setFundPriceAmt(rs.getDouble("fund_price_amt"));
            	fund.setFundValueUpDownAmt(rs.getDouble("fund_value_upDown_amt"));
            	fund.setFundValueUpDownPct(rs.getDouble("fund_value_upDown_pct"));
            	fund.setFundRate(rs.getDouble("fund_rate"));
            	fund.setFundFinalize(rs.getBoolean("fund_finalize"));
            	fund.setFundAccurateRate(rs.getDouble("fund_accurate_rate"));
            	fund.setFundValueToday(rs.getDouble("fund_value_today"));
            	
            	fund.setFundValueTmr(rs.getDouble("fund_value_tmr"));
            	fund.setFundPriceD0Avg(rs.getDouble("fund_price_d0_avg"));
            	fund.setFundPriceD1Avg(rs.getDouble("fund_price_d1_avg"));
            	fund.setFundPriceD2Avg(rs.getDouble("fund_price_d2_avg"));
            	fund.setFundDealARoi(rs.getDouble("fund_deal_a_roi"));
            	fund.setFundDealBRoi(rs.getDouble("fund_deal_b_roi"));
            	
            	result.add(fund);
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
	
	public void insertFund(Fund fund)
	{
		PreparedStatement prestmt = null;
		
		try {
			getConnection();
            prestmt = conn.prepareStatement("INSERT INTO `stock_2_fund_lof_data` ("
            		+ "`fund_id`, "
            		+ "`fund_name`, "
            		+ "`fund_date`, "
            		+ "`fund_price`, "
            		+ "`fund_value_yesterday`, "
            		+ "`fund_estimatedValue`, "
            		+ "`fund_price_upDown_amt`, "
            		+ "`fund_price_upDown_pct`, "
            		+ "`fund_price_vol`, "
            		+ "`fund_price_amt`, "
            		+ "`fund_value_upDown_amt`, "
            		+ "`fund_value_upDown_pct`, "
            		+ "`fund_rate`, "
            		+ "`fund_finalize`, "
            		+ "`fund_accurate_rate`, "
            		+ "`fund_value_today`, "
            		
            		+ "`fund_value_tmr`, "
            		+ "`fund_price_d0_avg`, "
            		+ "`fund_price_d1_avg`, "
            		+ "`fund_price_d2_avg`, "
            		+ "`fund_deal_a_roi`, "
            		+ "`fund_deal_b_roi` "
            		+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            prestmt.setString(1,fund.getFundId());
        	prestmt.setString(2,fund.getFundName());
        	prestmt.setTimestamp(3,new java.sql.Timestamp(fund.getFundDate().getTime()));
        	prestmt.setDouble(4,fund.getFundPrice());
        	prestmt.setDouble(5,fund.getFundValueYesterday());
        	prestmt.setDouble(6,fund.getFundEstimatedValue());
        	prestmt.setDouble(7,fund.getFundPriceUpDownAmt());
        	prestmt.setDouble(8,fund.getFundPriceUpDownPct());
        	prestmt.setDouble(9,fund.getFundPriceVol());
        	prestmt.setDouble(10,fund.getFundPriceAmt());
        	prestmt.setDouble(11,fund.getFundValueUpDownAmt());
        	prestmt.setDouble(12,fund.getFundValueUpDownPct());
        	prestmt.setDouble(13,fund.getFundRate());
        	prestmt.setBoolean(14,fund.isFundFinalize());
        	prestmt.setDouble(15,fund.getFundAccurateRate());
        	prestmt.setDouble(16,fund.getFundValueToday());
        	
        	prestmt.setDouble(17,fund.getFundValueTmr());
        	prestmt.setDouble(18,fund.getFundPriceD0Avg());
        	prestmt.setDouble(19,fund.getFundPriceD1Avg());
        	prestmt.setDouble(20,fund.getFundPriceD2Avg());
        	prestmt.setDouble(21,fund.getFundDealARoi());
        	prestmt.setDouble(22,fund.getFundDealBRoi());
        	
        	
        	prestmt.executeUpdate();
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Insert Fund Date into MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
            if (prestmt != null) {
                try {
                    prestmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Insert Fund Data into MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                prestmt = null;
            }
        }
	}
	
	public void updateFund(Fund fund)
	{
		PreparedStatement prestmt = null;
		
		try {
			getConnection();
            prestmt = conn.prepareStatement("UPDATE stock_2_fund_lof_data set"
            		+ " fund_finalize=?,"
            		+ " fund_rate=?,"
            		+ " fund_accurate_rate=?,"
            		+ " fund_value_today=?,"
            		
					+ " fund_value_tmr=?,"
					+ " fund_price_d0_avg=?,"
					+ " fund_price_d1_avg=?,"
					+ " fund_price_d2_avg=?,"
					+ " fund_deal_a_roi=?,"
					+ " fund_deal_b_roi=?"
					
            		+ " where id=?");
            
            prestmt.setBoolean(1,fund.isFundFinalize());
            prestmt.setDouble(2,fund.getFundRate());
            prestmt.setDouble(3,fund.getFundAccurateRate());
            prestmt.setDouble(4,fund.getFundValueToday());
            
            prestmt.setDouble(5,fund.getFundValueTmr());
            prestmt.setDouble(6,fund.getFundPriceD0Avg());
            prestmt.setDouble(7,fund.getFundPriceD1Avg());
            prestmt.setDouble(8,fund.getFundPriceD2Avg());
            prestmt.setDouble(9,fund.getFundDealARoi());
            prestmt.setDouble(10,fund.getFundDealBRoi());
            
        	prestmt.setInt(11,fund.getDbId());
        	
        	prestmt.executeUpdate();
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Update Fund to Finalize into MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
            if (prestmt != null) {
                try {
                    prestmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Update Fund to Finalize into MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                prestmt = null;
            }
        }
	}
	
	public void removeAllFund()
	{
		Statement stmt = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            stmt.execute("delete from stock_2_fund_lof_data where id<99999999999");
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Remove All Fund from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Remove All Fund from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
	}

}
