package runner;

import controller.Controller;
import controller.FullStockTOverallAvgVarController;
import dao.utility.DBConnectionManager;

public class FullStockTOverallAvgVarRunner {

	public static void main(String[] args) {
		Controller ctrl = new FullStockTOverallAvgVarController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();

	}

}
