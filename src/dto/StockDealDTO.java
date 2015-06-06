package dto;

import java.util.ArrayList;

import utility.date.DateUtil;
import utility.log.LoggerManager;
import dao.StockDealDBDAO;
import dto.calculator.BuyStockCalculator;
import dto.calculator.SellStockCalculator;
import dto.calculator.rule.RuleConfig;
import model.Stock;
import model.StockDeal;

public class StockDealDTO {
	public ArrayList <StockDeal> transformSellStockDealFromStockData(Stock stock)
	{
		// init
		StockDealDBDAO stockDealDao = new StockDealDBDAO();
		SellStockCalculator sellCal = new SellStockCalculator();
		ArrayList <StockDeal> result = new ArrayList <StockDeal>();
		ArrayList <StockDeal> dealList = null;
		
		dealList = stockDealDao.getIncompletedStockDealEBeforeBuyDate(stock.getStockId(), stock.getStockDate());
		for(StockDeal deal : dealList)
		{
			// record high variance in a wave
			if(deal.getBuyVar() < (stock.getStockAvg() - deal.getBuyPrice())/deal.getBuyPrice())
			{
				deal.setBuyVar((stock.getStockAvg() - deal.getBuyPrice())/deal.getBuyPrice());
			}
			
			/*
			if(sellCal.isGoodSell(stock, deal))
			{
				// good sell deals
				deal.setSellDate(stock.getStockDate());
				deal.setSellPrice(stock.getStockAvg());
				deal.setSellVar(stock.getStockPriceCostVar());
				deal.setComplete(true);
				deal.setSellType(StockDeal.GOOD_SELL);
				
				result.add(deal);
			}
			*/
			
			if(sellCal.isBadSell(stock, deal))
			{
				// bad sell deals
				deal.setSellDate(stock.getStockDate());
				deal.setSellPrice(stock.getStockAvg());
				deal.setSellVar((stock.getStockAvg() - deal.getBuyPrice())/deal.getBuyPrice());
				deal.setComplete(true);
				if((stock.getStockAvg() - deal.getBuyPrice())/deal.getBuyPrice()>0)
					deal.setSellType(StockDeal.GOOD_SELL);
				else
					deal.setSellType(StockDeal.BAD_SELL);
			}
			else if(sellCal.isMovingSell(stock, deal))
			{
				// moving sell deals
				deal.setSellDate(stock.getStockDate());
				deal.setSellPrice(stock.getStockAvg());
				deal.setSellVar((stock.getStockAvg() - deal.getBuyPrice())/deal.getBuyPrice());
				deal.setComplete(true);
				if((stock.getStockAvg() - deal.getBuyPrice())/deal.getBuyPrice()>0)
					deal.setSellType(StockDeal.GOOD_SELL);
				else
					deal.setSellType(StockDeal.BAD_SELL);
			}

			result.add(deal);
			
			/*
			if(sellCal.isLongSell(stock, deal))
			{
				// long sell deals
				deal.setSellDate(stock.getStockDate());
				deal.setSellPrice(stock.getStockAvg());
				deal.setSellVar(stock.getStockPriceCostVar());
				deal.setComplete(true);
				deal.setSellType(StockDeal.LONG_SELL);
				
				result.add(deal);
			}
			*/
		}
		
		return result;
	}

	public StockDeal transformBuyStockDealFromStockData(Stock stock)
	{
		// init
		BuyStockCalculator buyCal = new BuyStockCalculator();
		StockDeal buyDeal = null;
		
		try
		{
			// buy deals
			if(buyCal.isBuy(stock))
			{
				buyDeal = new StockDeal();
				
				buyDeal.setStockId(stock.getStockId());
				buyDeal.setBuyDate(stock.getStockDate());
				buyDeal.setBuyPrice(stock.getStockAvg());
				buyDeal.setBuyVar(-1);
				buyDeal.setSellDate(DateUtil.getDate("1900", "01", "01"));
				buyDeal.setSellPrice(0);
				buyDeal.setSellVar(0);
				buyDeal.setComplete(false);
				buyDeal.setSellType(StockDeal.BAD_SELL);
				
				if(stock.getStockAvg()*RuleConfig.getInstance().getBuyVol() < RuleConfig.getInstance().getBuyAmt())
				{
					buyDeal.setVolume(100*Math.ceil(RuleConfig.getInstance().getBuyAmt() / (stock.getStockAvg() * 100)));
				}
				else
				{
					buyDeal.setVolume(RuleConfig.getInstance().getBuyVol());
				}
			}
		}
		catch (Exception ex)
		{
			LoggerManager.getInstance().getLogger().error("Stock Buy Deal DTO Exception");
			LoggerManager.getInstance().getLogger().error(ex);
        	ex.printStackTrace();
		}
		
		return buyDeal;
	}
}
