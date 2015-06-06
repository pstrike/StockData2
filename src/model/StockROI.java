package model;

import java.util.Date;

public class StockROI {
	
	public static final int TYPE_INDIVIDUAL = 0;
	public static final int TYPE_OVERALL = 1;
	
	private int dbId;
	private int type;
	private String stockId;
	private double buyAmt;
	private double sellAmt;
	private double restAmt;
	private double roi;
	private int winTxnNo;
	private int loseTxnNo;
	private int totalTxnNo;
	private int sellTxnNo;
	private int restTxnNo;
	private int longTxnNo;
	private Date startDate;
	private Date endDate;
	
	public int getDbId() {
		return dbId;
	}
	public void setDbId(int dbId) {
		this.dbId = dbId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public double getBuyAmt() {
		return buyAmt;
	}
	public void setBuyAmt(double buyAmt) {
		this.buyAmt = buyAmt;
	}
	public double getSellAmt() {
		return sellAmt;
	}
	public void setSellAmt(double sellAmt) {
		this.sellAmt = sellAmt;
	}
	public double getRestAmt() {
		return restAmt;
	}
	public void setRestAmt(double restAmt) {
		this.restAmt = restAmt;
	}
	public double getRoi() {
		return roi;
	}
	public void setRoi(double roi) {
		this.roi = roi;
	}
	public int getWinTxnNo() {
		return winTxnNo;
	}
	public void setWinTxnNo(int winTxnNo) {
		this.winTxnNo = winTxnNo;
	}
	public int getLoseTxnNo() {
		return loseTxnNo;
	}
	public void setLoseTxnNo(int loseTxnNo) {
		this.loseTxnNo = loseTxnNo;
	}
	public int getTotalTxnNo() {
		return totalTxnNo;
	}
	public void setTotalTxnNo(int totalTxnNo) {
		this.totalTxnNo = totalTxnNo;
	}
	public int getSellTxnNo() {
		return sellTxnNo;
	}
	public void setSellTxnNo(int sellTxnNo) {
		this.sellTxnNo = sellTxnNo;
	}
	public int getRestTxnNo() {
		return restTxnNo;
	}
	public void setRestTxnNo(int restTxnNo) {
		this.restTxnNo = restTxnNo;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getLongTxnNo() {
		return longTxnNo;
	}
	public void setLongTxnNo(int longTxnNo) {
		this.longTxnNo = longTxnNo;
	}
}
