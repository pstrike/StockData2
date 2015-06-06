package model;

import java.util.Date;

public class StockDeal {
	public static final int GOOD_SELL = 2;
	public static final int LONG_SELL = 1;
	public static final int BAD_SELL = 0;
	
	private int dbId;
	private String stockId;
	private Date buyDate;
	private double buyPrice;
	private double buyVar;
	private Date sellDate;
	private double sellPrice;
	private double sellVar;
	private boolean isComplete;
	private double volume;
	private int sellType;
	public int getDbId() {
		return dbId;
	}
	public void setDbId(int dbId) {
		this.dbId = dbId;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public int getSellType() {
		return sellType;
	}
	public void setSellType(int sellType) {
		this.sellType = sellType;
	}
	public double getBuyVar() {
		return buyVar;
	}
	public void setBuyVar(double buyVar) {
		this.buyVar = buyVar;
	}
	public double getSellVar() {
		return sellVar;
	}
	public void setSellVar(double sellVar) {
		this.sellVar = sellVar;
	}
}
