package runner;

import controller.Controller;
import controller.RegularFundETLController;
import dao.utility.DBConnectionManager;

public class RegularFundETLRunner {

	public static void main(String[] args)
	{
		Controller ctrl = new RegularFundETLController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();

	}

}
