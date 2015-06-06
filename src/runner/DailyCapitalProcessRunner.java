package runner;

import controller.Controller;
import controller.DailyCapitalProcessController;
import dao.utility.DBConnectionManager;

public class DailyCapitalProcessRunner {

	public static void main(String[] args) {
		Controller ctrl = new DailyCapitalProcessController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();
	}

}
