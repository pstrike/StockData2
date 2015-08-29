package runner;

import controller.Controller;
import controller.Stock3GetStockDataController;
import dao.utility.DBConnectionManager;

public class Stock3GetStockDataRunner {
	
	public static void main(String[] args)
	{
		Controller ctrl = new Stock3GetStockDataController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();

	}
}
