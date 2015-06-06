package runner;

import controller.Controller;
import controller.FullStockTWaveCalculationController;
import dao.utility.DBConnectionManager;

public class FullStockTWaveCalculationRunner {

	public static void main(String[] args) {
		Controller ctrl = new FullStockTWaveCalculationController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();
	}

}
