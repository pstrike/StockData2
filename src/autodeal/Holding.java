package autodeal;

public class Holding {
	private String id;
	private int volumeTotal;
	private int volumeAvailable;
	private int volumeFrozen;
	private double priceCost;
	private double priceBalance;
	private double price;
	private double profitPct;
	private double profitAmt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getVolumeTotal() {
		return volumeTotal;
	}
	public void setVolumeTotal(int volumeTotal) {
		this.volumeTotal = volumeTotal;
	}
	public int getVolumeAvailable() {
		return volumeAvailable;
	}
	public void setVolumeAvailable(int volumeAvailable) {
		this.volumeAvailable = volumeAvailable;
	}
	public int getVolumeFrozen() {
		return volumeFrozen;
	}
	public void setVolumeFrozen(int volumeFrozen) {
		this.volumeFrozen = volumeFrozen;
	}
	public double getPriceCost() {
		return priceCost;
	}
	public void setPriceCost(double priceCost) {
		this.priceCost = priceCost;
	}
	public double getPriceBalance() {
		return priceBalance;
	}
	public void setPriceBalance(double priceBalance) {
		this.priceBalance = priceBalance;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getProfitPct() {
		return profitPct;
	}
	public void setProfitPct(double profitPct) {
		this.profitPct = profitPct;
	}
	public double getProfitAmt() {
		return profitAmt;
	}
	public void setProfitAmt(double profitAmt) {
		this.profitAmt = profitAmt;
	}
	
}
