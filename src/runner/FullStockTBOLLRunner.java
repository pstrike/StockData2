package runner;

import controller.Controller;
import controller.FullStockTBOLLController;
import dao.utility.DBConnectionManager;

public class FullStockTBOLLRunner {

	public static void main(String[] args) {
		Controller ctrl = new FullStockTBOLLController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();

	}

}
