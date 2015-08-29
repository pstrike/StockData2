package autodeal;

public class DealBuy {
	private String id;
	private int volume;
	private double price;
	private boolean isDone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	@Override
	public String toString() {
		return id+":"+price+":"+volume;
	}
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((DealBuy)obj).id);  
	}
}
