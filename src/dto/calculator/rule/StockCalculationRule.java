package dto.calculator.rule;

import model.Stock;
import model.StockDeal;

public abstract class StockCalculationRule {
	public abstract boolean executeRule(Stock thisStock, StockDeal deal);
}
