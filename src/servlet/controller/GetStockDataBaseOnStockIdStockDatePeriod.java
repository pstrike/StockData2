package servlet.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Stock;
import model.StockDeal;
import utility.date.DateUtil;
import utility.log.LoggerManager;
import dao.StockDBDAO;
import dao.StockDealDBDAO;

public class GetStockDataBaseOnStockIdStockDatePeriod extends StockController
{
	@Override
	public void action(HttpServletRequest req, HttpServletResponse resp)
	{
		// init
		StockDBDAO stockDbDao = new StockDBDAO();
		StockDealDBDAO stockDealDap = new StockDealDBDAO();
		JSONObject responseJsonList = new JSONObject();
		JSONArray dateJsonList = new JSONArray();
		JSONArray stockAvgJsonList = new JSONArray();
		JSONArray stockCostJsonList = new JSONArray();
		JSONArray stockLowCostJsonList = new JSONArray();
		JSONArray stockZeroJsonList = new JSONArray();
		//JSONArray stockOvarallJsonList = new JSONArray();
		JSONArray dealBuyDateJsonList = new JSONArray();
		JSONArray deaSellDateJsonList = new JSONArray();
		JSONArray stockMACDDIFJsonList = new JSONArray();
		JSONArray stockMACDDEAJsonList = new JSONArray();
		JSONArray stockBOLLMiddleJsonList = new JSONArray();
		JSONArray stockBOLLUpperJsonList = new JSONArray();
		JSONArray stockBOLLLowerJsonList = new JSONArray();
		
		//double temp = 0;
		boolean buyFlag = true;
		boolean sellFlag = true;
		
		try {
			// get param
			String stockId = req.getParameter("stockId");
			Date startDate = DateUtil.parse(req.getParameter("startDate"));
			Date endDate = DateUtil.parse(req.getParameter("endDate"));
				
			// process stock
			ArrayList<Stock> stockList = stockDbDao.getAscStockListBaseOnStockIdAndDatePeriod(stockId, startDate, endDate);
			//ArrayList<Stock> overallList = stockDbDao.getAscStockListBaseOnStockIdAndDatePeriod("overall", startDate, endDate);
			ArrayList<StockDeal> dealList = stockDealDap.getStockDealBetweenDateBaseOnStockId(stockId, startDate, endDate);
			
		    for(Stock stock : stockList)
		    {
		    	buyFlag = true;
		    	sellFlag = true;
		    	
		    	dateJsonList.put(DateUtil.format(stock.getStockDate()));
		    	stockAvgJsonList.put(String.valueOf(stock.getStockAvg()));
		    	stockCostJsonList.put(String.valueOf(stock.getStockEstimatedCost()));
		    	stockBOLLMiddleJsonList.put(String.valueOf(stock.getStockBollMiddle()/stock.getStockLowEstimatedCost()));
		    	stockBOLLUpperJsonList.put(String.valueOf(stock.getStockBollUpper()/stock.getStockLowEstimatedCost()));
		    	stockBOLLLowerJsonList.put(String.valueOf(stock.getStockBollLower()/stock.getStockLowEstimatedCost()));
		    	
		    	stockZeroJsonList.put(0);
		    	
		    	/*
		    	for(Stock st : overallList)
		    	{
		    		// put overall avg var in
		    		if(DateUtil.format(stock.getStockDate()).equals(DateUtil.format(st.getStockDate())))
		    		{
		    			stockOvarallJsonList.put(String.valueOf(st.getStockPriceCostVar()));
		    			temp = st.getStockPriceCostVar();
		    			continue;
		    		}
		    	}
		    	*/
		    	
		    	for(StockDeal deal : dealList)
			    {
		    		if(DateUtil.format(stock.getStockDate()).equals(DateUtil.format(deal.getBuyDate())))
		    		{
		    			dealBuyDateJsonList.put(stock.getStockAvg());
		    			buyFlag = false;
		    		}
			    }
		    	
		    	for(StockDeal deal : dealList)
			    {	
		    		if(DateUtil.format(stock.getStockDate()).equals(DateUtil.format(deal.getSellDate())))
			    	{
			    		deaSellDateJsonList.put(stock.getStockAvg());
			    		sellFlag = false;
			    		break;
			    	}
			    }
		    	
		    	if(buyFlag)
		    		dealBuyDateJsonList.put(0);
		    	
		    	if(sellFlag)
		    		deaSellDateJsonList.put(0);
		    	
		    	//stockLowCostJsonList.put(String.valueOf(stock.getStockEstimatedCost()*(0.8+temp)));
		    	stockLowCostJsonList.put(String.valueOf(stock.getStockLowEstimatedCost()));
		    	
		    	stockMACDDIFJsonList.put(stock.getStockMACDDIF());
		    	stockMACDDEAJsonList.put(stock.getStockMACDDEA());
		    }		    
		    
		    // form json response msg
		    responseJsonList.put("type", "gsid");
		    responseJsonList.put("stockId", stockId);
		    responseJsonList.put("startDate", startDate);
		    responseJsonList.put("endDate", endDate);
		    responseJsonList.put("dateList", dateJsonList);
		    responseJsonList.put("stockAvgList", stockAvgJsonList);
		    responseJsonList.put("stockCostList", stockCostJsonList);
		    responseJsonList.put("stockLowCostList", stockLowCostJsonList);
		    responseJsonList.put("stockZeroList", stockZeroJsonList);
		    //responseJsonList.put("stockOvarallList", stockOvarallJsonList);
		    responseJsonList.put("dealBuyDateList", dealBuyDateJsonList);
		    responseJsonList.put("dealSellDateList", deaSellDateJsonList);
		    responseJsonList.put("stockMACDDIFList", stockMACDDIFJsonList);
		    responseJsonList.put("stockMACDDEAList", stockMACDDEAJsonList);
		    responseJsonList.put("stockBOLLMiddleList", stockBOLLMiddleJsonList);
		    responseJsonList.put("stockBOLLUpperleList", stockBOLLUpperJsonList);
		    responseJsonList.put("stockBOLLLowerList", stockBOLLLowerJsonList);
		    
		    // form html response msg
		    resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println(responseJsonList.toString());
			out.close();
		}
		catch (Exception e)
		{
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
		
	}
}
