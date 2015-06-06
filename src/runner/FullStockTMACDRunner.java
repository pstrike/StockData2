package runner;

import controller.Controller;
import controller.FullStockTMACDController;
import dao.utility.DBConnectionManager;

public class FullStockTMACDRunner {

	public static void main(String[] args) {
		Controller ctrl = new FullStockTMACDController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();

	}

}
