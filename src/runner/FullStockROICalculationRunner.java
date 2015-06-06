package runner;

import controller.Controller;
import controller.FullStockROICalculationController;
import dao.utility.DBConnectionManager;

public class FullStockROICalculationRunner {

	public static void main(String[] args) {
		Controller ctrl = new FullStockROICalculationController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();
	}

}
