package model;

import java.util.Date;

import utility.date.DateUtil;

public class Fund {
	private int dbId;
	private Date fundDate;
	private String fundId;
	private String fundName;
	private String fundUrl;
	
	private double fundPrice;
	private double fundPriceUpDownAmt;
	private double fundPriceUpDownPct;
	private double fundPriceVol;
	private double fundPriceAmt;
	private double fundPriceD0Avg;
	private double fundPriceD1Avg;
	private double fundPriceD2Avg;
	
	private double fundValueYesterday;
	private double fundValueToday;
	private double fundValueTmr;
	private double fundValueUpDownAmt;
	private double fundValueUpDownPct;
	
	private double fundEstimatedValue;
	
	private double fundRate;
	private boolean isFundFinalize;
	private double fundAccurateRate;
	
	private double fundDealARoi;
	private double fundDealBRoi;
	
	private int fundHistoricalDealATxnNo;
	private double fundHistoricalDealAWinPct;
	private double fundHistoricalDealAWinRoi;
	private int fundHistoricalDealBTxnNo;
	private double fundHistoricalDealBWinPct;
	private double fundHistoricalDealBWinRoi;

	public int getDbId() {
		return dbId;
	}

	public void setDbId(int dbId) {
		this.dbId = dbId;
	}

	public Date getFundDate() {
		return fundDate;
	}

	public void setFundDate(Date fundDate) {
		this.fundDate = fundDate;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getFundUrl() {
		return fundUrl;
	}

	public void setFundUrl(String fundUrl) {
		this.fundUrl = fundUrl;
	}

	public double getFundPrice() {
		return fundPrice;
	}

	public void setFundPrice(double fundPrice) {
		this.fundPrice = fundPrice;
	}

	public double getFundPriceUpDownAmt() {
		return fundPriceUpDownAmt;
	}

	public void setFundPriceUpDownAmt(double fundPriceUpDownAmt) {
		this.fundPriceUpDownAmt = fundPriceUpDownAmt;
	}

	public double getFundPriceUpDownPct() {
		return fundPriceUpDownPct;
	}

	public void setFundPriceUpDownPct(double fundPriceUpDownPct) {
		this.fundPriceUpDownPct = fundPriceUpDownPct;
	}

	public double getFundPriceVol() {
		return fundPriceVol;
	}

	public void setFundPriceVol(double fundPriceVol) {
		this.fundPriceVol = fundPriceVol;
	}

	public double getFundPriceAmt() {
		return fundPriceAmt;
	}

	public void setFundPriceAmt(double fundPriceAmt) {
		this.fundPriceAmt = fundPriceAmt;
	}

	public double getFundValueYesterday() {
		return fundValueYesterday;
	}

	public void setFundValueYesterday(double fundValue) {
		this.fundValueYesterday = fundValue;
	}

	public double getFundValueUpDownAmt() {
		return fundValueUpDownAmt;
	}

	public void setFundValueUpDownAmt(double fundValueUpDownAmt) {
		this.fundValueUpDownAmt = fundValueUpDownAmt;
	}

	public double getFundValueUpDownPct() {
		return fundValueUpDownPct;
	}

	public void setFundValueUpDownPct(double fundValueUpDownPct) {
		this.fundValueUpDownPct = fundValueUpDownPct;
	}

	public double getFundEstimatedValue() {
		return fundEstimatedValue;
	}

	public void setFundEstimatedValue(double fundEstimatedValue) {
		this.fundEstimatedValue = fundEstimatedValue;
	}

	public double getFundRate() {
		return fundRate;
	}

	public void setFundRate(double fundRate) {
		this.fundRate = fundRate;
	}

	public boolean isFundFinalize() {
		return isFundFinalize;
	}

	public void setFundFinalize(boolean isFundFinalize) {
		this.isFundFinalize = isFundFinalize;
	}

	public double getFundAccurateRate() {
		return fundAccurateRate;
	}

	public void setFundAccurateRate(double fundAccurateRate) {
		this.fundAccurateRate = fundAccurateRate;
	}

	public double getFundValueToday() {
		return fundValueToday;
	}

	public void setFundValueToday(double fundValueToday) {
		this.fundValueToday = fundValueToday;
	}
	
	public String getFundJsonDate() {
		return DateUtil.format(fundDate, "yyyy-MM-dd hh:mm:ss");
	}

	public double getFundPriceD0Avg() {
		return fundPriceD0Avg;
	}

	public void setFundPriceD0Avg(double fundPriceD0Avg) {
		this.fundPriceD0Avg = fundPriceD0Avg;
	}

	public double getFundPriceD1Avg() {
		return fundPriceD1Avg;
	}

	public void setFundPriceD1Avg(double fundPriceD1Avg) {
		this.fundPriceD1Avg = fundPriceD1Avg;
	}

	public double getFundPriceD2Avg() {
		return fundPriceD2Avg;
	}

	public void setFundPriceD2Avg(double fundPriceD2Avg) {
		this.fundPriceD2Avg = fundPriceD2Avg;
	}

	public double getFundValueTmr() {
		return fundValueTmr;
	}

	public void setFundValueTmr(double fundValueTmr) {
		this.fundValueTmr = fundValueTmr;
	}

	public double getFundDealARoi() {
		return fundDealARoi;
	}

	public void setFundDealARoi(double fundDealARoi) {
		this.fundDealARoi = fundDealARoi;
	}

	public double getFundDealBRoi() {
		return fundDealBRoi;
	}

	public void setFundDealBRoi(double fundDealBRoi) {
		this.fundDealBRoi = fundDealBRoi;
	}

	public int getFundHistoricalDealATxnNo() {
		return fundHistoricalDealATxnNo;
	}

	public void setFundHistoricalDealATxnNo(int fundDealATxnNo) {
		this.fundHistoricalDealATxnNo = fundDealATxnNo;
	}

	public double getFundHistoricalDealAWinPct() {
		return fundHistoricalDealAWinPct;
	}

	public void setFundHistoricalDealAWinPct(double fundDealAWinPct) {
		this.fundHistoricalDealAWinPct = fundDealAWinPct;
	}

	public int getFundHistoricalDealBTxnNo() {
		return fundHistoricalDealBTxnNo;
	}

	public void setFundHistoricalDealBTxnNo(int fundDealBTxnNo) {
		this.fundHistoricalDealBTxnNo = fundDealBTxnNo;
	}

	public double getFundHistoricalDealBWinPct() {
		return fundHistoricalDealBWinPct;
	}

	public void setFundHistoricalDealBWinPct(double fundDealBWinPct) {
		this.fundHistoricalDealBWinPct = fundDealBWinPct;
	}

	public double getFundRateYesterday() {
		double fundPriceYesterday = fundPrice - fundPriceUpDownAmt;
		
		if(fundPriceYesterday>0)
			return (fundPriceYesterday-fundValueYesterday)/fundPriceYesterday;
		else
			return 0;
	}

	public double getFundHistoricalDealAWinRoi() {
		return fundHistoricalDealAWinRoi;
	}

	public void setFundHistoricalDealAWinRoi(double fundHistoricalDealAWinRoi) {
		this.fundHistoricalDealAWinRoi = fundHistoricalDealAWinRoi;
	}

	public double getFundHistoricalDealBWinRoi() {
		return fundHistoricalDealBWinRoi;
	}

	public void setFundHistoricalDealBWinRoi(double fundHistoricalDealBWinRoi) {
		this.fundHistoricalDealBWinRoi = fundHistoricalDealBWinRoi;
	}
}
