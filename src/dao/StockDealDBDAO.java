package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.StockDeal;
import utility.date.DateUtil;
import utility.log.LoggerManager;
import dao.utility.DBConnectionManager;

public class StockDealDBDAO {
	private Connection conn;
	
	public void getConnection()
	{
		conn = DBConnectionManager.getInstance().getConnection();
	}
	
	public void insertBuyDeal(StockDeal deal)
	{
		PreparedStatement prestmt = null;
		
		try {
			getConnection();
			prestmt = conn.prepareStatement("INSERT INTO `stock_2_deal` ("
            		+ "`stock_id`, "
            		+ "`deal_buy_date`, "
            		+ "`deal_buy_price`, "
            		+ "`deal_buy_var`, "
            		+ "`deal_sell_date`, "
            		+ "`deal_sell_price`, "
            		+ "`deal_sell_var`, "
            		+ "`deal_volume`, "
            		+ "`deal_isComplete`,"
            		+ "`deal_sell_type`"
            		+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            prestmt.setString(1,deal.getStockId());
        	prestmt.setDate(2,new java.sql.Date(deal.getBuyDate().getTime()));
        	prestmt.setDouble(3,deal.getBuyPrice());
        	prestmt.setDouble(4,deal.getBuyVar());
        	prestmt.setDate(5,new java.sql.Date(deal.getSellDate().getTime()));
        	prestmt.setDouble(6,deal.getSellPrice());
        	prestmt.setDouble(7,deal.getSellVar());
        	prestmt.setDouble(8,deal.getVolume());
        	prestmt.setBoolean(9,deal.isComplete());
        	prestmt.setInt(10,deal.getSellType());
        	
        	prestmt.executeUpdate();
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Insert Stock Buy Deal into MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
            if (prestmt != null) {
                try {
                    prestmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Insert Stock Buy Deal into MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                prestmt = null;
            }
        }
	}
	
	public void updateSellDeal(StockDeal deal)
	{
		PreparedStatement prestmt = null;
		
		try {
			getConnection();
			prestmt = conn.prepareStatement("update stock_2_deal set "
            		+ "deal_sell_date=?, "
            		+ "deal_sell_price=?, "
            		+ "deal_sell_var=?, "
            		+ "deal_isComplete=?, "
            		+ "deal_sell_type=?, "
            		+ "deal_buy_var=? "
            		+ "where id = ?");
            
        	prestmt.setDate(1,new java.sql.Date(deal.getSellDate().getTime()));
        	prestmt.setDouble(2,deal.getSellPrice());
        	prestmt.setDouble(3,deal.getSellVar());
        	prestmt.setBoolean(4,deal.isComplete());
        	prestmt.setInt(5,deal.getSellType());
        	prestmt.setDouble(6,deal.getBuyVar());
            prestmt.setInt(7,deal.getDbId());
        	
        	prestmt.executeUpdate();
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Update Stock Sell Deal into MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
            if (prestmt != null) {
                try {
                    prestmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Update Stock Sell Deal into MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                prestmt = null;
            }
        }
	}
	
	public ArrayList<StockDeal> getIncompletedStockDealEBeforeBuyDate(String stockId, Date date)
	{
		ArrayList<StockDeal> result = new ArrayList<StockDeal>();
		StockDeal deal = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
			
            rs = stmt.executeQuery("select * from stock_2_deal where stock_id="
            						+ "'"+stockId+"' and deal_buy_date <= '"+DateUtil.format(date)+"' and deal_isComplete=false");
            
            while(rs.next())
            {
            	deal = new StockDeal();
            	
            	deal.setDbId(rs.getInt("id"));
            	deal.setStockId(rs.getString("stock_id"));
            	deal.setBuyDate(new java.util.Date(rs.getDate("deal_buy_date").getTime()));
            	deal.setBuyPrice(rs.getDouble("deal_buy_price"));
            	deal.setBuyVar(rs.getDouble("deal_buy_var"));
            	deal.setSellDate(new java.util.Date(rs.getDate("deal_sell_date").getTime()));
            	deal.setSellPrice(rs.getDouble("deal_sell_price"));
            	deal.setComplete(rs.getBoolean("deal_isComplete"));
            	deal.setVolume(rs.getDouble("deal_volume"));
            	deal.setSellType(rs.getInt("deal_sell_type"));
            	
            	result.add(deal);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Incompleted Stock Deal Base on Stock Buy Price and Sell Type Equal Before Buy Date from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Incompleted Stock Deal Base on Stock Buy Price and Sell Type Equal Before Buy Date from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Incompleted Stock Deal Base on Stock Buy Price and Sell Type Equal Before Buy Date from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public ArrayList<StockDeal> getStockDealBetweenDate(Date startDate, Date endDate, Date roiStartDate, Date roiEndDate)
	{
		ArrayList<StockDeal> result = new ArrayList<StockDeal>();
		StockDeal deal = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
			
            rs = stmt.executeQuery("select * from stock_2_deal where "
            						+ "(deal_buy_date >= '"+DateUtil.format(startDate)+"' and deal_buy_date <= '"+DateUtil.format(endDate)+"')"
            						+ "or (deal_sell_date >= '"+DateUtil.format(startDate)+"' and deal_sell_date <= '"+DateUtil.format(endDate)+"' and deal_buy_date >= '"+DateUtil.format(roiStartDate)+"' and deal_buy_date <= '"+DateUtil.format(roiEndDate)+"') order by deal_buy_date asc, deal_isComplete desc");
            
            while(rs.next())
            {
            	deal = new StockDeal();
            	
            	deal.setDbId(rs.getInt("id"));
            	deal.setStockId(rs.getString("stock_id"));
            	deal.setBuyDate(new java.util.Date(rs.getDate("deal_buy_date").getTime()));
            	deal.setBuyPrice(rs.getDouble("deal_buy_price"));
            	deal.setSellDate(new java.util.Date(rs.getDate("deal_sell_date").getTime()));
            	deal.setSellPrice(rs.getDouble("deal_sell_price"));
            	deal.setComplete(rs.getBoolean("deal_isComplete"));
            	deal.setVolume(rs.getDouble("deal_volume"));
            	deal.setSellType(rs.getInt("deal_sell_type"));
            	
            	result.add(deal);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Stock Deal Base On Date from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Stock Deal Base On Date from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Stock Deal Base On Date from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public int getStockDealCountBetweenDate(Date startDate, Date endDate)
	{
		int result = 0;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
			
            rs = stmt.executeQuery("select count(*) from stock_2_deal where "
            						+ "deal_buy_date >= '"+DateUtil.format(startDate)+"' and deal_buy_date <= '"+DateUtil.format(endDate)+"'");
            
            rs.next();

            result = rs.getInt("count(*)");
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Stock Deal Count Base On Date from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Stock Deal Count Base On Date from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Stock Deal Count Base On Date from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public ArrayList<StockDeal> getStockDealBetweenDateBaseOnStockId(String stockId, Date startDate, Date endDate)
	{
		ArrayList<StockDeal> result = new ArrayList<StockDeal>();
		StockDeal deal = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
			
            rs = stmt.executeQuery("select * from stock_2_deal where "
            						+ "stock_id = '"+stockId+"' and "
            						+ "deal_buy_date >= '"+DateUtil.format(startDate)+"' and deal_buy_date <= '"+DateUtil.format(endDate)+"'");
            
            while(rs.next())
            {
            	deal = new StockDeal();
            	
            	deal.setDbId(rs.getInt("id"));
            	deal.setStockId(rs.getString("stock_id"));
            	deal.setBuyDate(new java.util.Date(rs.getDate("deal_buy_date").getTime()));
            	deal.setBuyPrice(rs.getDouble("deal_buy_price"));
            	deal.setSellDate(new java.util.Date(rs.getDate("deal_sell_date").getTime()));
            	deal.setSellPrice(rs.getDouble("deal_sell_price"));
            	deal.setComplete(rs.getBoolean("deal_isComplete"));
            	deal.setVolume(rs.getDouble("deal_volume"));
            	deal.setSellType(rs.getInt("deal_sell_type"));
            	
            	result.add(deal);
            }
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Get Stock Deal Base on Date and Stock ID from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Get Stock Deal Base on Date and Stock ID from MySQL Exception");
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
                	LoggerManager.getInstance().getLogger().error("Get Stock Deal Base on Date and Stock ID from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
		
		return result;
	}
	
	public void removeAllStockDeal()
	{
		Statement stmt = null;
		
		try {
			getConnection();
			stmt = conn.createStatement();
            stmt.execute("delete from stock_2_deal where id<99999999999");
            
        } catch (Exception ex) {
        	LoggerManager.getInstance().getLogger().error("Remove All Stock Deal from MySQL Exception");
        	LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
        } finally {
        	
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx)
                {
                	LoggerManager.getInstance().getLogger().error("Remove All Stock Deal from MySQL Exception");
                	LoggerManager.getInstance().getLogger().error(sqlEx);
                	sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
	}
}
