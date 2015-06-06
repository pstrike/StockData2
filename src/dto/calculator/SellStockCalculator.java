package dto.calculator;

import java.util.ArrayList;

import dto.calculator.rule.BadSellLowCostStockSellRule;
import dto.calculator.rule.BadSellStockSellRule;
import dto.calculator.rule.GoodSellStockSellRule;
import dto.calculator.rule.LongSellStockSellRule;
import dto.calculator.rule.MovingSellRule;
import dto.calculator.rule.StockCalculationRule;
import dto.calculator.rule.WaveTrendDownStockSellRule;
import model.Stock;
import model.StockDeal;

public class SellStockCalculator {

	public boolean isGoodSell(Stock stock, StockDeal deal)
	{
		boolean result = true;
		
		ArrayList <StockCalculationRule> rules = new ArrayList<StockCalculationRule> ();
		
		rules.add(new GoodSellStockSellRule());
		
		for(StockCalculationRule rule : rules)
    	{
			result = result && rule.executeRule(stock, deal);
    	}	
		
		return result;
	}
	
	public boolean isBadSell(Stock stock, StockDeal deal)
	{
		boolean result = true;
		
		ArrayList <StockCalculationRule> rules = new ArrayList<StockCalculationRule> ();
		
		//rules.add(new BadSellStockSellRule());
		rules.add(new BadSellLowCostStockSellRule());
		
		for(StockCalculationRule rule : rules)
    	{
			result = result && rule.executeRule(stock, deal);
    	}	
		
		return result;
	}
	
	public boolean isLongSell(Stock stock, StockDeal deal)
	{
		boolean result = true;
		
		ArrayList <StockCalculationRule> rules = new ArrayList<StockCalculationRule> ();
		
		rules.add(new LongSellStockSellRule());
		
		for(StockCalculationRule rule : rules)
    	{
			result = result && rule.executeRule(stock, deal);
    	}	
		
		return result;
	}
	
	public boolean isMovingSell(Stock stock, StockDeal deal)
	{
		boolean result = true;
		
		ArrayList <StockCalculationRule> rules = new ArrayList<StockCalculationRule> ();
		
		//rules.add(new MovingSellRule());
		rules.add(new WaveTrendDownStockSellRule());
		
		for(StockCalculationRule rule : rules)
    	{
			result = result && rule.executeRule(stock, deal);
    	}	
		
		return result;
	}
	
}
