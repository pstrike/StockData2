package runner;

import controller.Controller;
import controller.FullFundTRateCalculationController;
import dao.utility.DBConnectionManager;

public class FullFundTRateCalculationRunner {

	public static void main(String[] args)
	{
		Controller ctrl = new FullFundTRateCalculationController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();

	}

}
