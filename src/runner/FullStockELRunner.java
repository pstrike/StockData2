package runner;

import controller.Controller;
import controller.FullStockELController;
import dao.utility.DBConnectionManager;

public class FullStockELRunner {

	public static void main(String[] args) {
		Controller ctrl = new FullStockELController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();
	}

}
