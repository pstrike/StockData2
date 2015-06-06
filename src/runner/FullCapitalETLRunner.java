package runner;

import controller.Controller;
import controller.FullCapitalETLController;
import dao.utility.DBConnectionManager;

public class FullCapitalETLRunner {
	
	public static void main(String[] args) {
		Controller ctrl = new FullCapitalETLController();
		ctrl.action();
		
		// close DB connection if open before
		DBConnectionManager.getInstance().closeConnection();
	}
	
}
