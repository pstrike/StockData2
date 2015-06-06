package runner;

import controller.Controller;
import controller.FullStockVarDeltaCalculatorController;
import dao.utility.DBConnectionManager;

public class FullStockVarDeltaCalculatorRunner {

	public static void main(String[] args)
	{
		Controller ctrl = new FullStockVarDeltaCalculatorController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();

	}

}
