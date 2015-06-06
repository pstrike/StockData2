package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.Stock;
import utility.date.DateUtil;
import utility.log.LoggerManager;
import dao.utility.DBConnectionManager;

public class StockDBDAO {
	private Connection conn;
	
	public void getConnection()
	{
		conn = DBConnectionManager.getInstance().getConnection();
	}
	
	public void insertRawStock(Stock stock)
	{
		PreparedStatement prestmt = null;
		
		try {
			getConnection();
            prestmt = conn.prepareStatement("INSERT INTO `stock_2_data` ("
            		+ "`stock_date`, "
            		+ "`stock_id`, "
            		+ "`stock_opening`, "
            		+ "`stock_high`, "
            		+ "`stock_low`, "
            		+ "`stock_close`, "
            		+ "`stock_avg`, "
            		+ "`stock_upDownPct`, "
            		+ "`stock_upDownAmt`, "
            		+ "`stock_turnoverRate`, "
            		+ "`stock_volume`, "
            		+ "`stock_amount`, "
            		+ "`stock_totalStockVol`, "
            		+ "`stock_totalStockAmt`, "
            		+ "`stock_issuedStockVol`, "
            		+ "`stock_issuedStockAmt`, "
            		+ "`stock_estimatedCost`,"
            		+ "stock_variance,"
            		+ "stock_movingAvgVar,"
            		+ "stock_buyIndex,"
            		+ "stock_macd_dif,"
            		+ "stock_macd_dea,"
            		+ "stock_macd,"
            		+ "stock_macd_fast_ema,"
            		+ "stock_macd_slow_ema,"
            		+ "stock_variance_delta,"
            		+ "stock_low_estimatedCost,"
            		+ "stock_low_variance,"
            		+ "stock_low_variance_delta,"
            		+ "stock_boll_middle,"
            		+ "stock_boll_upper,"
            		+ "stock_boll_lower,"
            		+ "stock_wave_high_avg,"
            		+ "stock_wave_last_one_avg,"
            		+ "stock_wave_last_two_avg, "
            		+ "stock_wave_last_one_low_avg, "
            		+ "stock_wave_last_two_low_avg, "
            		+ "stock_low_estimatedCost_upDownAmt, "
            		+ "stock_macd_last_one "
            		+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            prestmt.setDate(1,new java.sql.Date(stock.getStockDate().getTime()));
        	prestmt.setString(2,stock.getStockId());
        	prestmt.setDouble(3,stock.getStockOpen());
        	prestmt.setDouble(4,stock.getStockHigh());
        	prestmt.setDouble(5,stock.getStockLow());
        	prestmt.setDouble(6,stock.getStockClose());
        	prestmt.setDouble(7,stock.getStockAvg());
        	prestmt.setDouble(8,stock.getStockUpDownPct());
        	prestmt.setDouble(9,stock.getStockUpDownAmt());
        	prestmt.setDouble(10,stock.getStockTurnoverRate());
        	prestmt.setDouble(11,stock.getStockVol());
        	prestmt.setDouble(12,stock.getStockAmt());
        	prestmt.setDouble(13,stock.getStockTotalVol());
        	prestmt.setDouble(14,stock.getStockTotalAmt());
        	prestmt.setDouble(15,stock.getStockAIssueVol());
        	prestmt.setDouble(16,stock.getStockAIssueAmt());
        	prestmt.setDouble(17,stock.getStockEstimatedCost());
        	prestmt.setDouble(18,stock.getStockPriceCostVar());
        	prestmt.setDouble(19,stock.getStockMovingPriceCostVarAvg());
        	prestmt.setDouble(20,stock.getStockBuyIndex());
        	prestmt.setDouble(21,stock.getStockMACDDIF());
        	prestmt.setDouble(22,stock.getStockMACDDEA());
        	prestmt.setDouble(23,stock.getStockMACD());
        	prestmt.setDouble(24,stock.getStockMACDFastEMA());
        	prestmt.setDouble(25,stock.getStockMACDSlowEMA());
        	prestmt.setDouble(26,stock.getStockPriceCostVarDelta());
        	prestmt.setDouble(27,stock.getStockLowEstimatedCost());
        	prestmt.setDouble(28,stock.getStockLowVariance());
        	prestmt.setDouble(29,stock.getStockLowVarianceDelta());
        	prestmt.setDouble(30,stock.getStockBollMiddle());
        	prestmt.setDouble(31,stock.getStockBollUpper());
        	prestmt.setDouble(32,stock.getStockBollLower());
        	prestmt.setDouble(33,stock.getStockWaveHighAvg());
        	prestmt.setDouble(34,stock.getStockWaveLastOneAvg());
        	prestmt.setDouble(35,stock.getStockWaveLastTwoAvg());
        	prestmt.setDouble(36,stock.getStockWaveLastOneLowAvg());
        	prestmt.setDouble(37,stock.getStockWaveLastTwoLowAvg());
        	prestmt.setDouble(38,stock.getStockLowEstimatedCostUpDownAmt());
        	prestmt.setDouble(39,stock.getStockMACDLastOne());
        	
        	prestmt.executeUpdate();
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Insert Raw Stock Data into MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
            if (prestmt != null) {
                try {
                    prestmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Insert Raw Stock Data into MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                prestmt = null;
            }
        }
	}
	
	public void updateStock(Stock stock)
	{
		PreparedStatement prestmt = null;
		
		try {
			getConnection();
            prestmt = conn.prepareStatement("UPDATE stock_2_data set"
            		+ "`stock_date`=?, "
            		+ "`stock_avg`=?, "
            		+ "`stock_upDownPct`=?, "
            		+ "`stock_upDownAmt`=?, "
            		+ "`stock_turnoverRate`=?, "
            		+ "`stock_totalStockVol`=?, "
            		+ "`stock_totalStockAmt`=?, "
            		+ "`stock_issuedStockVol`=?, "
            		+ "`stock_issuedStockAmt`=?, "
            		+ "`stock_estimatedCost`=?,"
            		+ "stock_variance=?,"
            		+ "stock_movingAvgVar=?,"
            		+ "stock_buyIndex=?,"
            		+ "stock_macd_dif=?,"
            		+ "stock_macd_dea=?,"
            		+ "stock_macd=?,"
            		+ "stock_macd_fast_ema=?,"
            		+ "stock_macd_slow_ema=?,"
            		+ "stock_variance_delta=?, "
            		+ "stock_low_estimatedCost=?, "
            		+ "stock_low_variance=?, "
            		+ "stock_low_variance_delta=?, "
            		+ "stock_boll_middle=?, "
            		+ "stock_boll_upper=?, "
            		+ "stock_boll_lower=?, "
            		+ "stock_wave_high_avg=?, "
            		+ "stock_wave_last_one_avg=?, "
            		+ "stock_wave_last_two_avg=?, "
            		+ "stock_wave_last_one_low_avg=?, "
            		+ "stock_wave_last_two_low_avg=?, "
            		+ "stock_low_estimatedCost_upDownAmt=?, "
            		+ "stock_macd_last_one=? "
            		+ "where id = ?");
            
            prestmt.setDate(1,new java.sql.Date(stock.getStockDate().getTime()));
        	prestmt.setDouble(2,stock.getStockAvg());
        	prestmt.setDouble(3,stock.getStockUpDownPct());
        	prestmt.setDouble(4,stock.getStockUpDownAmt());
        	prestmt.setDouble(5,stock.getStockTurnoverRate());
        	prestmt.setDouble(6,stock.getStockTotalVol());
        	prestmt.setDouble(7,stock.getStockTotalAmt());
        	prestmt.setDouble(8,stock.getStockAIssueVol());
        	prestmt.setDouble(9,stock.getStockAIssueAmt());
        	prestmt.setDouble(10,stock.getStockEstimatedCost());
        	prestmt.setDouble(11,stock.getStockPriceCostVar());
        	prestmt.setDouble(12,stock.getStockMovingPriceCostVarAvg());
        	prestmt.setDouble(13,stock.getStockBuyIndex());
        	prestmt.setDouble(14,stock.getStockMACDDIF());
        	prestmt.setDouble(15,stock.getStockMACDDEA());
        	prestmt.setDouble(16,stock.getStockMACD());
        	prestmt.setDouble(17,stock.getStockMACDFastEMA());
        	prestmt.setDouble(18,stock.getStockMACDSlowEMA());
        	prestmt.setDouble(19,stock.getStockPriceCostVarDelta());
        	prestmt.setDouble(20,stock.getStockLowEstimatedCost());
        	prestmt.setDouble(21,stock.getStockLowVariance());
        	prestmt.setDouble(22,stock.getStockLowVarianceDelta());
        	prestmt.setDouble(23,stock.getStockBollMiddle());
        	prestmt.setDouble(24,stock.getStockBollUpper());
        	prestmt.setDouble(25,stock.getStockBollLower());
        	prestmt.setDouble(26,stock.getStockWaveHighAvg());
        	prestmt.setDouble(27,stock.getStockWaveLastOneAvg());
        	prestmt.setDouble(28,stock.getStockWaveLastTwoAvg());
        	prestmt.setDouble(29,stock.getStockWaveLastOneLowAvg());
        	prestmt.setDouble(30,stock.getStockWaveLastTwoLowAvg());
        	prestmt.setDouble(31,stock.getStockLowEstimatedCostUpDownAmt());
        	prestmt.setDouble(32,stock.getStockMACDLastOne());
        	prestmt.setInt(33,stock.getDbId());
        	
        	prestmt.executeUpdate();
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Update Stock Data into MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
            if (prestmt != null) {
                try {
                    prestmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Update Stock Data into MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                prestmt = null;
            }
        }
	}
	
	public ArrayList<String> getAllStockId()
	{
		ArrayList<String> result = new ArrayList<String>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select distinct stock_id from stock_2_data");
            
            while(rs.next())
            {
            	result.add(rs.getString("stock_id"));
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get All Stock Id from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get All Stock Id from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get All Stock Id from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public ArrayList<Stock> getAscStockListBaseOnStockId(String stockId)
	{
		ArrayList<Stock> result = new ArrayList<Stock>();
		Stock stock = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_data where stock_id='"+stockId+"' order by stock_date asc");
            
            while(rs.next())
            {
            	stock = new Stock();
            	stock.setDbId(rs.getInt("id"));
            	stock.setStockDate(new java.util.Date(rs.getDate("stock_date").getTime()));
            	stock.setStockId(rs.getString("stock_id"));
            	stock.setStockOpen(rs.getDouble("stock_opening"));
            	stock.setStockHigh(rs.getDouble("stock_high"));
            	stock.setStockLow(rs.getDouble("stock_low"));
            	stock.setStockClose(rs.getDouble("stock_close"));
            	stock.setStockAvg(rs.getDouble("stock_avg"));
            	stock.setStockUpDownPct(rs.getDouble("stock_upDownPct"));
            	stock.setStockUpDownAmt(rs.getDouble("stock_upDownAmt"));
            	stock.setStockTurnoverRate(rs.getDouble("stock_turnoverRate"));
            	stock.setStockVol(rs.getDouble("stock_volume"));
            	stock.setStockAmt(rs.getDouble("stock_amount"));
            	stock.setStockTotalVol(rs.getDouble("stock_totalStockVol"));
            	stock.setStockTotalAmt(rs.getDouble("stock_totalStockAmt"));
            	stock.setStockAIssueVol(rs.getDouble("stock_issuedStockVol"));
            	stock.setStockAIssueAmt(rs.getDouble("stock_issuedStockAmt"));
            	stock.setStockEstimatedCost(rs.getDouble("stock_estimatedCost"));
            	stock.setStockPriceCostVar(rs.getDouble("stock_variance"));
            	stock.setStockMovingPriceCostVarAvg(rs.getDouble("stock_movingAvgVar"));
            	stock.setStockBuyIndex(rs.getDouble("stock_buyIndex"));
            	stock.setStockMACDDIF(rs.getDouble("stock_macd_dif"));
            	stock.setStockMACDDEA(rs.getDouble("stock_macd_dea"));
            	stock.setStockMACD(rs.getDouble("stock_macd"));
            	stock.setStockMACDFastEMA(rs.getDouble("stock_macd_fast_ema"));
            	stock.setStockMACDSlowEMA(rs.getDouble("stock_macd_slow_ema"));
            	stock.setStockPriceCostVarDelta(rs.getDouble("stock_variance_delta"));
            	stock.setStockLowEstimatedCost(rs.getDouble("stock_low_estimatedCost"));
            	stock.setStockLowVariance(rs.getDouble("stock_low_variance"));
            	stock.setStockLowVarianceDelta(rs.getDouble("stock_low_variance_delta"));
            	stock.setStockBollMiddle(rs.getDouble("stock_boll_middle"));
            	stock.setStockBollUpper(rs.getDouble("stock_boll_upper"));
            	stock.setStockBollLower(rs.getDouble("stock_boll_lower"));
            	stock.setStockWaveHighAvg(rs.getDouble("stock_wave_high_avg"));
            	stock.setStockWaveLastOneAvg(rs.getDouble("stock_wave_last_one_avg"));
            	stock.setStockWaveLastTwoAvg(rs.getDouble("stock_wave_last_two_avg"));
            	stock.setStockWaveLastOneLowAvg(rs.getDouble("stock_wave_last_one_low_avg"));
            	stock.setStockWaveLastTwoLowAvg(rs.getDouble("stock_wave_last_two_low_avg"));
            	stock.setStockLowEstimatedCostUpDownAmt(rs.getDouble("stock_low_estimatedCost_upDownAmt"));
            	stock.setStockMACDLastOne(rs.getDouble("stock_macd_last_one"));
            	
            	result.add(stock);
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
	
	public Stock getLatestStockBaseOnStockId(String stockId)
	{
		Stock result = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_data where stock_id='"+stockId+"' order by stock_date desc limit 1");
            
            rs.next();

            result = new Stock();
            result.setDbId(rs.getInt("id"));
            result.setStockDate(new java.util.Date(rs.getDate("stock_date").getTime()));
            result.setStockId(rs.getString("stock_id"));
            result.setStockOpen(rs.getDouble("stock_opening"));
            result.setStockHigh(rs.getDouble("stock_high"));
            result.setStockLow(rs.getDouble("stock_low"));
            result.setStockClose(rs.getDouble("stock_close"));
            result.setStockAvg(rs.getDouble("stock_avg"));
            result.setStockUpDownPct(rs.getDouble("stock_upDownPct"));
            result.setStockUpDownAmt(rs.getDouble("stock_upDownAmt"));
            result.setStockTurnoverRate(rs.getDouble("stock_turnoverRate"));
            result.setStockVol(rs.getDouble("stock_volume"));
            result.setStockAmt(rs.getDouble("stock_amount"));
            result.setStockTotalVol(rs.getDouble("stock_totalStockVol"));
            result.setStockTotalAmt(rs.getDouble("stock_totalStockAmt"));
            result.setStockAIssueVol(rs.getDouble("stock_issuedStockVol"));
            result.setStockAIssueAmt(rs.getDouble("stock_issuedStockAmt"));
            result.setStockEstimatedCost(rs.getDouble("stock_estimatedCost"));
           	result.setStockPriceCostVar(rs.getDouble("stock_variance"));
           	result.setStockMovingPriceCostVarAvg(rs.getDouble("stock_movingAvgVar"));
           	result.setStockBuyIndex(rs.getDouble("stock_buyIndex"));
           	result.setStockMACDDIF(rs.getDouble("stock_macd_dif"));
           	result.setStockMACDDEA(rs.getDouble("stock_macd_dea"));
           	result.setStockMACD(rs.getDouble("stock_macd"));
           	result.setStockMACDFastEMA(rs.getDouble("stock_macd_fast_ema"));
        	result.setStockMACDSlowEMA(rs.getDouble("stock_macd_slow_ema"));
        	result.setStockPriceCostVarDelta(rs.getDouble("stock_variance_delta"));
        	result.setStockLowEstimatedCost(rs.getDouble("stock_low_estimatedCost"));
        	result.setStockLowVariance(rs.getDouble("stock_low_variance"));
        	result.setStockLowVarianceDelta(rs.getDouble("stock_low_variance_delta"));
        	result.setStockBollMiddle(rs.getDouble("stock_boll_middle"));
        	result.setStockBollUpper(rs.getDouble("stock_boll_upper"));
        	result.setStockBollLower(rs.getDouble("stock_boll_lower"));
        	result.setStockWaveHighAvg(rs.getDouble("stock_wave_high_avg"));
        	result.setStockWaveLastOneAvg(rs.getDouble("stock_wave_last_one_avg"));
        	result.setStockWaveLastTwoAvg(rs.getDouble("stock_wave_last_two_avg"));
        	result.setStockWaveLastOneLowAvg(rs.getDouble("stock_wave_last_one_low_avg"));
        	result.setStockWaveLastTwoLowAvg(rs.getDouble("stock_wave_last_two_low_avg"));
        	result.setStockLowEstimatedCostUpDownAmt(rs.getDouble("stock_low_estimatedCost_upDownAmt"));
        	result.setStockMACDLastOne(rs.getDouble("stock_macd_last_one"));
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Latet Specific Stock Data from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Latet Specific Stock Data from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Latet Specific Stock Data from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public Stock getStockBaseOnStockIdAtDayBefore(String stockId, int dayNo)
	{
		Stock result = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_data where stock_id='"+stockId+"' order by stock_date desc limit "+dayNo);
            
            rs.last();

            result = new Stock();
            result.setDbId(rs.getInt("id"));
            result.setStockDate(new java.util.Date(rs.getDate("stock_date").getTime()));
            result.setStockId(rs.getString("stock_id"));
            result.setStockOpen(rs.getDouble("stock_opening"));
            result.setStockHigh(rs.getDouble("stock_high"));
            result.setStockLow(rs.getDouble("stock_low"));
            result.setStockClose(rs.getDouble("stock_close"));
            result.setStockAvg(rs.getDouble("stock_avg"));
            result.setStockUpDownPct(rs.getDouble("stock_upDownPct"));
            result.setStockUpDownAmt(rs.getDouble("stock_upDownAmt"));
            result.setStockTurnoverRate(rs.getDouble("stock_turnoverRate"));
            result.setStockVol(rs.getDouble("stock_volume"));
            result.setStockAmt(rs.getDouble("stock_amount"));
            result.setStockTotalVol(rs.getDouble("stock_totalStockVol"));
            result.setStockTotalAmt(rs.getDouble("stock_totalStockAmt"));
            result.setStockAIssueVol(rs.getDouble("stock_issuedStockVol"));
            result.setStockAIssueAmt(rs.getDouble("stock_issuedStockAmt"));
            result.setStockEstimatedCost(rs.getDouble("stock_estimatedCost"));
           	result.setStockPriceCostVar(rs.getDouble("stock_variance"));
           	result.setStockMovingPriceCostVarAvg(rs.getDouble("stock_movingAvgVar"));
           	result.setStockBuyIndex(rs.getDouble("stock_buyIndex"));
           	result.setStockMACDDIF(rs.getDouble("stock_macd_dif"));
           	result.setStockMACDDEA(rs.getDouble("stock_macd_dea"));
           	result.setStockMACD(rs.getDouble("stock_macd"));
           	result.setStockMACDFastEMA(rs.getDouble("stock_macd_fast_ema"));
        	result.setStockMACDSlowEMA(rs.getDouble("stock_macd_slow_ema"));
        	result.setStockPriceCostVarDelta(rs.getDouble("stock_variance_delta"));
        	result.setStockLowEstimatedCost(rs.getDouble("stock_low_estimatedCost"));
        	result.setStockLowVariance(rs.getDouble("stock_low_variance"));
        	result.setStockLowVarianceDelta(rs.getDouble("stock_low_variance_delta"));
        	result.setStockBollMiddle(rs.getDouble("stock_boll_middle"));
        	result.setStockBollUpper(rs.getDouble("stock_boll_upper"));
        	result.setStockBollLower(rs.getDouble("stock_boll_lower"));
        	result.setStockWaveHighAvg(rs.getDouble("stock_wave_high_avg"));
        	result.setStockWaveLastOneAvg(rs.getDouble("stock_wave_last_one_avg"));
        	result.setStockWaveLastTwoAvg(rs.getDouble("stock_wave_last_two_avg"));
        	result.setStockWaveLastOneLowAvg(rs.getDouble("stock_wave_last_one_low_avg"));
        	result.setStockWaveLastTwoLowAvg(rs.getDouble("stock_wave_last_two_low_avg"));
        	result.setStockLowEstimatedCostUpDownAmt(rs.getDouble("stock_low_estimatedCost_upDownAmt"));
        	result.setStockMACDLastOne(rs.getDouble("stock_macd_last_one"));
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Latet Specific Stock Data from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Latet Specific Stock Data from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Latet Specific Stock Data from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public Stock getStockBaseOnStockIdAndDate(String stockId, Date date)
	{
		Stock result = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_data where stock_id='"+stockId+"' and stock_date<='"+DateUtil.format(date)+"' order by stock_date desc limit 1");
            
            if(rs.next())
            {
            	result = new Stock();
                result.setDbId(rs.getInt("id"));
                result.setStockDate(new java.util.Date(rs.getDate("stock_date").getTime()));
                result.setStockId(rs.getString("stock_id"));
                result.setStockOpen(rs.getDouble("stock_opening"));
                result.setStockHigh(rs.getDouble("stock_high"));
                result.setStockLow(rs.getDouble("stock_low"));
                result.setStockClose(rs.getDouble("stock_close"));
                result.setStockAvg(rs.getDouble("stock_avg"));
                result.setStockUpDownPct(rs.getDouble("stock_upDownPct"));
                result.setStockUpDownAmt(rs.getDouble("stock_upDownAmt"));
                result.setStockTurnoverRate(rs.getDouble("stock_turnoverRate"));
                result.setStockVol(rs.getDouble("stock_volume"));
                result.setStockAmt(rs.getDouble("stock_amount"));
                result.setStockTotalVol(rs.getDouble("stock_totalStockVol"));
                result.setStockTotalAmt(rs.getDouble("stock_totalStockAmt"));
                result.setStockAIssueVol(rs.getDouble("stock_issuedStockVol"));
                result.setStockAIssueAmt(rs.getDouble("stock_issuedStockAmt"));
                result.setStockEstimatedCost(rs.getDouble("stock_estimatedCost"));
               	result.setStockPriceCostVar(rs.getDouble("stock_variance"));
               	result.setStockMovingPriceCostVarAvg(rs.getDouble("stock_movingAvgVar"));
               	result.setStockBuyIndex(rs.getDouble("stock_buyIndex"));
               	result.setStockMACDDIF(rs.getDouble("stock_macd_dif"));
               	result.setStockMACDDEA(rs.getDouble("stock_macd_dea"));
               	result.setStockMACD(rs.getDouble("stock_macd"));
               	result.setStockMACDFastEMA(rs.getDouble("stock_macd_fast_ema"));
            	result.setStockMACDSlowEMA(rs.getDouble("stock_macd_slow_ema"));
            	result.setStockPriceCostVarDelta(rs.getDouble("stock_variance_delta"));
            	result.setStockLowEstimatedCost(rs.getDouble("stock_low_estimatedCost"));
            	result.setStockLowVariance(rs.getDouble("stock_low_variance"));
            	result.setStockLowVarianceDelta(rs.getDouble("stock_low_variance_delta"));
            	result.setStockBollMiddle(rs.getDouble("stock_boll_middle"));
            	result.setStockBollUpper(rs.getDouble("stock_boll_upper"));
            	result.setStockBollLower(rs.getDouble("stock_boll_lower"));
            	result.setStockWaveHighAvg(rs.getDouble("stock_wave_high_avg"));
            	result.setStockWaveLastOneAvg(rs.getDouble("stock_wave_last_one_avg"));
            	result.setStockWaveLastTwoAvg(rs.getDouble("stock_wave_last_two_avg"));
            	result.setStockWaveLastOneLowAvg(rs.getDouble("stock_wave_last_one_low_avg"));
            	result.setStockWaveLastTwoLowAvg(rs.getDouble("stock_wave_last_two_low_avg"));
            	result.setStockLowEstimatedCostUpDownAmt(rs.getDouble("stock_low_estimatedCost_upDownAmt"));
            	result.setStockMACDLastOne(rs.getDouble("stock_macd_last_one"));
            } 
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Stock Data base on Date from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Stock Data base on Date from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Stock Data base on Date from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public ArrayList<Stock> getAscStockListBaseOnStockIdAndDatePeriod(String stockId, Date startDate, Date endDate)
	{
		ArrayList<Stock> result = new ArrayList<Stock>();
		Stock stock = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_data where stock_id='"+stockId+"' and stock_date>='"+DateUtil.format(startDate)+"' and stock_date<='"+DateUtil.format(endDate)+"' order by stock_date asc");
            
            while(rs.next())
            {
            	stock = new Stock();
            	stock.setDbId(rs.getInt("id"));
            	stock.setStockDate(new java.util.Date(rs.getDate("stock_date").getTime()));
            	stock.setStockId(rs.getString("stock_id"));
            	stock.setStockOpen(rs.getDouble("stock_opening"));
            	stock.setStockHigh(rs.getDouble("stock_high"));
            	stock.setStockLow(rs.getDouble("stock_low"));
            	stock.setStockClose(rs.getDouble("stock_close"));
            	stock.setStockAvg(rs.getDouble("stock_avg"));
            	stock.setStockUpDownPct(rs.getDouble("stock_upDownPct"));
            	stock.setStockUpDownAmt(rs.getDouble("stock_upDownAmt"));
            	stock.setStockTurnoverRate(rs.getDouble("stock_turnoverRate"));
            	stock.setStockVol(rs.getDouble("stock_volume"));
            	stock.setStockAmt(rs.getDouble("stock_amount"));
            	stock.setStockTotalVol(rs.getDouble("stock_totalStockVol"));
            	stock.setStockTotalAmt(rs.getDouble("stock_totalStockAmt"));
            	stock.setStockAIssueVol(rs.getDouble("stock_issuedStockVol"));
            	stock.setStockAIssueAmt(rs.getDouble("stock_issuedStockAmt"));
            	stock.setStockEstimatedCost(rs.getDouble("stock_estimatedCost"));
            	stock.setStockPriceCostVar(rs.getDouble("stock_variance"));
            	stock.setStockMovingPriceCostVarAvg(rs.getDouble("stock_movingAvgVar"));
            	stock.setStockBuyIndex(rs.getDouble("stock_buyIndex"));
            	stock.setStockMACDDIF(rs.getDouble("stock_macd_dif"));
            	stock.setStockMACDDEA(rs.getDouble("stock_macd_dea"));
            	stock.setStockMACD(rs.getDouble("stock_macd"));
            	stock.setStockMACDFastEMA(rs.getDouble("stock_macd_fast_ema"));
            	stock.setStockMACDSlowEMA(rs.getDouble("stock_macd_slow_ema"));
            	stock.setStockPriceCostVarDelta(rs.getDouble("stock_variance_delta"));
            	stock.setStockLowEstimatedCost(rs.getDouble("stock_low_estimatedCost"));
            	stock.setStockLowVariance(rs.getDouble("stock_low_variance"));
            	stock.setStockLowVarianceDelta(rs.getDouble("stock_low_variance_delta"));
            	stock.setStockBollMiddle(rs.getDouble("stock_boll_middle"));
            	stock.setStockBollUpper(rs.getDouble("stock_boll_upper"));
            	stock.setStockBollLower(rs.getDouble("stock_boll_lower"));
            	stock.setStockWaveHighAvg(rs.getDouble("stock_wave_high_avg"));
            	stock.setStockWaveLastOneAvg(rs.getDouble("stock_wave_last_one_avg"));
            	stock.setStockWaveLastTwoAvg(rs.getDouble("stock_wave_last_two_avg"));
            	stock.setStockWaveLastOneLowAvg(rs.getDouble("stock_wave_last_one_low_avg"));
            	stock.setStockWaveLastTwoLowAvg(rs.getDouble("stock_wave_last_two_low_avg"));
            	stock.setStockLowEstimatedCostUpDownAmt(rs.getDouble("stock_low_estimatedCost_upDownAmt"));
            	stock.setStockMACDLastOne(rs.getDouble("stock_macd_last_one"));
            	
            	result.add(stock);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Stock Data base on Date from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Stock Data base on Date from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Stock Data base on Date from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public Date getLatestStockDate()
	{
		Date result = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select distinct max(stock_date) from stock_2_data");
            
            rs.next();

            result = new java.util.Date(rs.getDate("max(stock_date)").getTime());
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Lastest Stock Date from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Lastest Stock Date from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Lastest Stock Date from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public Date getEarliestStockDate()
	{
		Date result = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select distinct min(stock_date) from stock_2_data");
            
            rs.next();

            result = new java.util.Date(rs.getDate("min(stock_date)").getTime());
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Earliest Stock Date from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Earliest Stock Date from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Earliest Stock Date from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public ArrayList<Date> getAscStockDateList()
	{
		ArrayList <Date> result = new ArrayList<Date>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select distinct stock_date from stock_2_data order by stock_date asc");
            
            while(rs.next())
            {
            	 result.add(new java.util.Date(rs.getDate("stock_date").getTime()));
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Stock Date List from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Stock Date List from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Stock Date List from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public ArrayList<Stock> getStockListBaseOnDate(Date date)
	{
		ArrayList<Stock> result = new ArrayList<Stock>();
		Stock stock = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from stock_2_data where stock_date = '"+DateUtil.format(date)+"'");
            
            while(rs.next())
            {
            	stock = new Stock();
            	stock.setDbId(rs.getInt("id"));
            	stock.setStockDate(new java.util.Date(rs.getDate("stock_date").getTime()));
            	stock.setStockId(rs.getString("stock_id"));
            	stock.setStockOpen(rs.getDouble("stock_opening"));
            	stock.setStockHigh(rs.getDouble("stock_high"));
            	stock.setStockLow(rs.getDouble("stock_low"));
            	stock.setStockClose(rs.getDouble("stock_close"));
            	stock.setStockAvg(rs.getDouble("stock_avg"));
            	stock.setStockUpDownPct(rs.getDouble("stock_upDownPct"));
            	stock.setStockUpDownAmt(rs.getDouble("stock_upDownAmt"));
            	stock.setStockTurnoverRate(rs.getDouble("stock_turnoverRate"));
            	stock.setStockVol(rs.getDouble("stock_volume"));
            	stock.setStockAmt(rs.getDouble("stock_amount"));
            	stock.setStockTotalVol(rs.getDouble("stock_totalStockVol"));
            	stock.setStockTotalAmt(rs.getDouble("stock_totalStockAmt"));
            	stock.setStockAIssueVol(rs.getDouble("stock_issuedStockVol"));
            	stock.setStockAIssueAmt(rs.getDouble("stock_issuedStockAmt"));
            	stock.setStockEstimatedCost(rs.getDouble("stock_estimatedCost"));
            	stock.setStockPriceCostVar(rs.getDouble("stock_variance"));
            	stock.setStockMovingPriceCostVarAvg(rs.getDouble("stock_movingAvgVar"));
            	stock.setStockBuyIndex(rs.getDouble("stock_buyIndex"));
            	stock.setStockMACDDIF(rs.getDouble("stock_macd_dif"));
            	stock.setStockMACDDEA(rs.getDouble("stock_macd_dea"));
            	stock.setStockMACD(rs.getDouble("stock_macd"));
            	stock.setStockMACDFastEMA(rs.getDouble("stock_macd_fast_ema"));
            	stock.setStockMACDSlowEMA(rs.getDouble("stock_macd_slow_ema"));
            	stock.setStockPriceCostVarDelta(rs.getDouble("stock_variance_delta"));
            	stock.setStockLowEstimatedCost(rs.getDouble("stock_low_estimatedCost"));
            	stock.setStockLowVariance(rs.getDouble("stock_low_variance"));
            	stock.setStockLowVarianceDelta(rs.getDouble("stock_low_variance_delta"));
            	stock.setStockBollMiddle(rs.getDouble("stock_boll_middle"));
            	stock.setStockBollUpper(rs.getDouble("stock_boll_upper"));
            	stock.setStockBollLower(rs.getDouble("stock_boll_lower"));
            	stock.setStockWaveHighAvg(rs.getDouble("stock_wave_high_avg"));
            	stock.setStockWaveLastOneAvg(rs.getDouble("stock_wave_last_one_avg"));
            	stock.setStockWaveLastTwoAvg(rs.getDouble("stock_wave_last_two_avg"));
            	stock.setStockWaveLastOneLowAvg(rs.getDouble("stock_wave_last_one_low_avg"));
            	stock.setStockWaveLastTwoLowAvg(rs.getDouble("stock_wave_last_two_low_avg"));
            	stock.setStockLowEstimatedCostUpDownAmt(rs.getDouble("stock_low_estimatedCost_upDownAmt"));
            	stock.setStockMACDLastOne(rs.getDouble("stock_macd_last_one"));
            	
            	result.add(stock);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Stock List Base On Date from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Stock List Base On Date from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Stock List Base On Date from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public double getOverallStockVarAverage(Date date)
	{
		double result = 0;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select avg(stock_variance) from stock_2_data where stock_date = '"+DateUtil.format(date)+"'");
            
            rs.next();

            result = rs.getDouble("avg(stock_variance)");
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Stock Var Avg from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Stock Var Avg from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Stock Var Avg from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public double getHighetMACDDIFInLastWAVEByDate(String stockId, Date date)
	{
		double result = 0;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery("select distinct max(stock_macd_dif) from stock_2_data where stock_id='"+stockId+"' and stock_date > (select distinct max(stock_date) from stock_2_data where stock_macd_dif < 0.0001 and stock_id='"+stockId+"' and stock_date <= '"+DateUtil.format(date)+"') and stock_date<='"+DateUtil.format(date)+"'");
            
            rs.next();

            result = rs.getDouble("max(stock_macd_dif)");
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Highest MACD DIF from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Highest MACD DIF from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Highest MACD DIF from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
}
