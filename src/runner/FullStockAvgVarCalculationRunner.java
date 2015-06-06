package runner;

import controller.Controller;
import controller.FullStockAvgVarCalculationController;
import dao.utility.DBConnectionManager;

public class FullStockAvgVarCalculationRunner {

	public static void main(String[] args) {
		Controller ctrl = new FullStockAvgVarCalculationController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();
	}
	
}
