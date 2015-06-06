package model;

public class FundROI {
	private int dbId;
	private double rateUpper;
	private double rateLower;
	private int winTxnNo;
	private double roi;
	private int dealTxnNo;
	private int totalTxnNo;
	private String type;
	private boolean isUptrend;
	
	public FundROI(double rateUpper, double rateLower, String type, boolean isUptrend)
	{
		this.rateUpper=rateUpper;
		this.rateLower=rateLower;
		this.setType(type);
		this.isUptrend = isUptrend;
		this.setRoi(0);
		this.winTxnNo=0;
		this.dealTxnNo=0;
		this.totalTxnNo=0;
	}
	
	public FundROI() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		/*if(isUptrend)
			return "["+type+"类套利 - 溢价率上升趋势 - 溢价率区间："+(int)(rateLower*100)+"% to "+(int)(rateUpper*100)+"%]";
		else
			return "["+type+"类套利 - 溢价率下降趋势 - 溢价率区间："+(int)(rateLower*100)+"% to "+(int)(rateUpper*100)+"%]";*/
		
		if(isUptrend)
			return "上升";
		else
			return "下降";
	}
	public double getRateUpper() {
		return rateUpper;
	}
	public void setRateUpper(double rateUpper) {
		this.rateUpper = rateUpper;
	}
	public double getRateLower() {
		return rateLower;
	}
	public void setRateLower(double rateLower) {
		this.rateLower = rateLower;
	}

	public int getDealTxnNo() {
		return dealTxnNo;
	}

	public void setDealTxnNo(int dealTxnNo) {
		this.dealTxnNo = dealTxnNo;
	}

	public int getTotalTxnNo() {
		return totalTxnNo;
	}

	public void setTotalTxnNo(int totalTxnNo) {
		this.totalTxnNo = totalTxnNo;
	}

	public double getDealPct() {
		if(totalTxnNo>0)
			return dealTxnNo*1.0/totalTxnNo;
		else
			return 0;
	}

	public double getWinPct() {
		if(dealTxnNo>0)
			return winTxnNo*1.0/dealTxnNo;
		else
			return 0;
	}

	public double getExpectation() {
		return getDealPct()*getWinPct();
	}

	public double getRoi() {
		return roi;
	}

	public void setRoi(double roi) {
		this.roi = roi;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWinTxnNo() {
		return winTxnNo;
	}

	public void setWinTxnNo(int winTxnNo) {
		this.winTxnNo = winTxnNo;
	}

	public boolean isUptrend() {
		return isUptrend;
	}

	public void setUptrend(boolean isUptrend) {
		this.isUptrend = isUptrend;
	}

	public int getDbId() {
		return dbId;
	}

	public void setDbId(int dbId) {
		this.dbId = dbId;
	}
}
