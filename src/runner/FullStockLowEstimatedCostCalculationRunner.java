package runner;

import controller.Controller;
import controller.FullStockLowEstimatedCostCalculationController;
import dao.utility.DBConnectionManager;

public class FullStockLowEstimatedCostCalculationRunner {

	public static void main(String[] args)
	{
		Controller ctrl = new FullStockLowEstimatedCostCalculationController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();

	}

}
