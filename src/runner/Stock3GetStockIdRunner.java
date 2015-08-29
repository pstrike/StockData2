package runner;

import controller.Controller;
import controller.Stock3GetStockIdListController;
import dao.utility.DBConnectionManager;

public class Stock3GetStockIdRunner {
	
	public static void main(String[] args)
	{
		Controller ctrl = new Stock3GetStockIdListController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();

	}

}
