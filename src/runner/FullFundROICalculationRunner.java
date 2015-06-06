package runner;

import controller.Controller;
import controller.FullFundROICalculationController;
import dao.utility.DBConnectionManager;

public class FullFundROICalculationRunner {

	public static void main(String[] args)
	{
		Controller ctrl = new FullFundROICalculationController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();

	}

}
