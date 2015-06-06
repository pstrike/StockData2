package runner;

import controller.Controller;
import controller.DailyStockProcessController;
import dao.utility.DBConnectionManager;

public class DailyStockProcessRunner {

	public static void main(String[] args) {
		Controller ctrl = new DailyStockProcessController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();
	}

}
