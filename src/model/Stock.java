package model;

import java.util.Date;

public class Stock {
	private int dbId;
	private Date stockDate;
	private String stockId;
	private double stockOpen;
	private double stockHigh;
	private double stockLow;
	private double stockClose;
	private double stockAvg;
	private double stockUpDownAmt;
	private double stockUpDownPct;
	private double stockAmt;
	private double stockVol;
	private double stockTurnoverRate;
	private double stockTotalAmt;
	private double stockTotalVol;
	private double stockAIssueAmt;
	private double stockAIssueVol;
	private double stockEstimatedCost;
	private double stockPriceCostVar;
	private double stockPriceCostVarDelta;
	private double stockMovingPriceCostVarAvg;
	private double stockBuyIndex;
	private double stockMACDDIF;
	private double stockMACDDEA;
	private double stockMACD;
	private double stockMACDLastOne;
	private double stockMACDFastEMA;
	private double stockMACDSlowEMA;
	private double stockLowEstimatedCost;
	private double stockLowEstimatedCostUpDownAmt;
	private double stockLowVariance;
	private double stockLowVarianceDelta;
	private double stockBollMiddle;
	private double stockBollUpper;
	private double stockBollLower;
	private double stockWaveHighAvg;
	private double stockWaveLastOneAvg;
	private double stockWaveLastTwoAvg;
	private double stockWaveLastOneLowAvg;
	private double stockWaveLastTwoLowAvg;
	
	public int getDbId() {
		return dbId;
	}
	public void setDbId(int dbId) {
		this.dbId = dbId;
	}
	public Date getStockDate() {
		return stockDate;
	}
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public double getStockOpen() {
		return stockOpen;
	}
	public void setStockOpen(double stockOpen) {
		this.stockOpen = stockOpen;
	}
	public double getStockHigh() {
		return stockHigh;
	}
	public void setStockHigh(double stockHigh) {
		this.stockHigh = stockHigh;
	}
	public double getStockLow() {
		return stockLow;
	}
	public void setStockLow(double stockLow) {
		this.stockLow = stockLow;
	}
	public double getStockClose() {
		return stockClose;
	}
	public void setStockClose(double stockClose) {
		this.stockClose = stockClose;
	}
	public double getStockAvg() {
		return stockAvg;
	}
	public void setStockAvg(double stockAvg) {
		this.stockAvg = stockAvg;
	}
	public double getStockUpDownAmt() {
		return stockUpDownAmt;
	}
	public void setStockUpDownAmt(double stockUpDownAmt) {
		this.stockUpDownAmt = stockUpDownAmt;
	}
	public double getStockUpDownPct() {
		return stockUpDownPct;
	}
	public void setStockUpDownPct(double stockUpDownPct) {
		this.stockUpDownPct = stockUpDownPct;
	}
	public double getStockAmt() {
		return stockAmt;
	}
	public void setStockAmt(double stockAmt) {
		this.stockAmt = stockAmt;
	}
	public double getStockVol() {
		return stockVol;
	}
	public void setStockVol(double stockVol) {
		this.stockVol = stockVol;
	}
	public double getStockTurnoverRate() {
		return stockTurnoverRate;
	}
	public void setStockTurnoverRate(double stockTurnoverRate) {
		this.stockTurnoverRate = stockTurnoverRate;
	}
	public double getStockTotalAmt() {
		return stockTotalAmt;
	}
	public void setStockTotalAmt(double stockTotalAmt) {
		this.stockTotalAmt = stockTotalAmt;
	}
	public double getStockTotalVol() {
		return stockTotalVol;
	}
	public void setStockTotalVol(double stockTotalVol) {
		this.stockTotalVol = stockTotalVol;
	}
	public double getStockAIssueAmt() {
		return stockAIssueAmt;
	}
	public void setStockAIssueAmt(double stockAIssueAmt) {
		this.stockAIssueAmt = stockAIssueAmt;
	}
	public double getStockAIssueVol() {
		return stockAIssueVol;
	}
	public void setStockAIssueVol(double stockAIssueVol) {
		this.stockAIssueVol = stockAIssueVol;
	}
	public double getStockEstimatedCost() {
		return stockEstimatedCost;
	}
	public void setStockEstimatedCost(double stockEstimatedCost) {
		this.stockEstimatedCost = stockEstimatedCost;
	}
	public double getStockPriceCostVar() {
		return stockPriceCostVar;
	}
	public void setStockPriceCostVar(double stockPriceCostVar) {
		this.stockPriceCostVar = stockPriceCostVar;
	}
	public double getStockMovingPriceCostVarAvg() {
		return stockMovingPriceCostVarAvg;
	}
	public void setStockMovingPriceCostVarAvg(double stockMovingPriceCostVarAvg) {
		this.stockMovingPriceCostVarAvg = stockMovingPriceCostVarAvg;
	}
	public double getStockBuyIndex() {
		return stockBuyIndex;
	}
	public void setStockBuyIndex(double stockBuyIndex) {
		this.stockBuyIndex = stockBuyIndex;
	}
	public double getStockMACDDIF() {
		return stockMACDDIF;
	}
	public void setStockMACDDIF(double stockMACDDIF) {
		this.stockMACDDIF = stockMACDDIF;
	}
	public double getStockMACDDEA() {
		return stockMACDDEA;
	}
	public void setStockMACDDEA(double stockMACDDEA) {
		this.stockMACDDEA = stockMACDDEA;
	}
	public double getStockMACD() {
		return stockMACD;
	}
	public void setStockMACD(double stockMACD) {
		this.stockMACD = stockMACD;
	}
	public double getStockMACDFastEMA() {
		return stockMACDFastEMA;
	}
	public void setStockMACDFastEMA(double stockMACDFastEMA) {
		this.stockMACDFastEMA = stockMACDFastEMA;
	}
	public double getStockMACDSlowEMA() {
		return stockMACDSlowEMA;
	}
	public void setStockMACDSlowEMA(double stockMACDSlowEMA) {
		this.stockMACDSlowEMA = stockMACDSlowEMA;
	}
	public double getStockPriceCostVarDelta() {
		return stockPriceCostVarDelta;
	}
	public void setStockPriceCostVarDelta(double stockPriceCostVarDelta) {
		this.stockPriceCostVarDelta = stockPriceCostVarDelta;
	}
	public double getStockLowEstimatedCost() {
		return stockLowEstimatedCost;
	}
	public void setStockLowEstimatedCost(double stockLowEstimatedCost) {
		this.stockLowEstimatedCost = stockLowEstimatedCost;
	}
	public double getStockLowVariance() {
		return stockLowVariance;
	}
	public void setStockLowVariance(double stockLowVariance) {
		this.stockLowVariance = stockLowVariance;
	}
	public double getStockLowVarianceDelta() {
		return stockLowVarianceDelta;
	}
	public void setStockLowVarianceDelta(double stockLowVarianceDelta) {
		this.stockLowVarianceDelta = stockLowVarianceDelta;
	}
	public double getStockBollMiddle() {
		return stockBollMiddle;
	}
	public void setStockBollMiddle(double stockBollMiddle) {
		this.stockBollMiddle = stockBollMiddle;
	}
	public double getStockBollUpper() {
		return stockBollUpper;
	}
	public void setStockBollUpper(double stockBollUpper) {
		this.stockBollUpper = stockBollUpper;
	}
	public double getStockBollLower() {
		return stockBollLower;
	}
	public void setStockBollLower(double stockBollLower) {
		this.stockBollLower = stockBollLower;
	}
	public double getStockWaveHighAvg() {
		return stockWaveHighAvg;
	}
	public void setStockWaveHighAvg(double stockWaveHighAvg) {
		this.stockWaveHighAvg = stockWaveHighAvg;
	}
	public double getStockWaveLastOneAvg() {
		return stockWaveLastOneAvg;
	}
	public void setStockWaveLastOneAvg(double stockWaveLastOneAvg) {
		this.stockWaveLastOneAvg = stockWaveLastOneAvg;
	}
	public double getStockWaveLastTwoAvg() {
		return stockWaveLastTwoAvg;
	}
	public void setStockWaveLastTwoAvg(double stockWaveLastTwoAvg) {
		this.stockWaveLastTwoAvg = stockWaveLastTwoAvg;
	}
	public double getStockWaveLastOneLowAvg() {
		return stockWaveLastOneLowAvg;
	}
	public void setStockWaveLastOneLowAvg(double stockWaveLastOneLowAvg) {
		this.stockWaveLastOneLowAvg = stockWaveLastOneLowAvg;
	}
	public double getStockWaveLastTwoLowAvg() {
		return stockWaveLastTwoLowAvg;
	}
	public void setStockWaveLastTwoLowAvg(double stockWaveLastTwoLowAvg) {
		this.stockWaveLastTwoLowAvg = stockWaveLastTwoLowAvg;
	}
	public double getStockLowEstimatedCostUpDownAmt() {
		return stockLowEstimatedCostUpDownAmt;
	}
	public void setStockLowEstimatedCostUpDownAmt(
			double stockLowEstimatedCostUpDownAmt) {
		this.stockLowEstimatedCostUpDownAmt = stockLowEstimatedCostUpDownAmt;
	}
	public double getStockMACDLastOne() {
		return stockMACDLastOne;
	}
	public void setStockMACDLastOne(double stockMACDLastOne) {
		this.stockMACDLastOne = stockMACDLastOne;
	}
	
}
