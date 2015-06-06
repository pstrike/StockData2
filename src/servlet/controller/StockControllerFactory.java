package servlet.controller;

import java.util.HashMap;

import utility.log.LoggerManager;

public class StockControllerFactory {

	public HashMap<String, String> routeMapping;
	
	public StockControllerFactory()
	{
		this.routeMapping = new HashMap<String, String>();
		this.routeMapping.put("gsid", "servlet.controller.GetStockDataBaseOnStockIdStockDatePeriod");
		this.routeMapping.put("gfd", "servlet.controller.GetFundDataController");
		this.routeMapping.put("gsfd", "servlet.controller.GetFundDataBaseOnFundIdController");
		this.routeMapping.put("gsfroi", "servlet.controller.GetFundROIController");
	}
	
	public StockController createController(String type)
	{
		StockController result = null;

		try {
			result = (StockController) Class.forName( routeMapping.get(type) ).newInstance();
		} catch (InstantiationException e) {
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
		
		return result;
	}
}
