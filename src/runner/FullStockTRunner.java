package runner;

import controller.Controller;
import controller.FullStockTController;
import dao.utility.DBConnectionManager;

public class FullStockTRunner {
	
	public static void main(String[] args) {
		Controller ctrl = new FullStockTController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();
	}
	
}
