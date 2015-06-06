package runner;

import controller.Controller;
import controller.FullStockDealCalculationController;
import dao.utility.DBConnectionManager;

public class FullStockDealCalculationRunner {
	
	public static void main(String[] args) {
		Controller ctrl = new FullStockDealCalculationController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();
	}

}
