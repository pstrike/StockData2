package autodeal;

import java.util.ArrayList;

public class Broker {
	private Buyer buyer;
	private Seller seller;
	private Balance balance;
	private Order order;
	
	public Broker()
	{
		buyer = new Buyer();
		seller = new Seller();
		balance = new Balance();
		order = new Order();
	}
	
	public void buy(ArrayList<DealBuy> deals)
	{
		buyer.buy(deals);
	}
	
	public void sell(ArrayList<DealSell> deals)
	{
		seller.sell(deals);
	}
	
	public ArrayList<Holding> getBalance()
	{
		return balance.getBalance();
	}
	
	public ArrayList<DealBuy> getBuyOrder()
	{
		return order.getBuyOrder();
	}
}
