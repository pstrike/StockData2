package model;

import java.util.Date;

public class Capital {
	private int dbId;
	private String stockId;
	private Date capitalDate;
	private double stockTotalVolume;
	private double stockAIssueVolume;
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
	public Date getCapitalDate() {
		return capitalDate;
	}
	public void setCapitalDate(Date capitalDate) {
		this.capitalDate = capitalDate;
	}
	public double getStockTotalVolume() {
		return stockTotalVolume;
	}
	public void setStockTotalVolume(double stockTotalVolume) {
		this.stockTotalVolume = stockTotalVolume;
	}
	public double getStockAIssueVolume() {
		return stockAIssueVolume;
	}
	public void setStockAIssueVolume(double stockAIssueVolume) {
		this.stockAIssueVolume = stockAIssueVolume;
	}
}
