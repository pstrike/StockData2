package servlet.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FundROI;

import org.json.JSONArray;

import utility.json.JsonConverter;
import utility.log.LoggerManager;
import dao.FundROIDBDAO;

public class GetFundROIController extends StockController
{
	@Override
	public void action(HttpServletRequest req, HttpServletResponse resp)
	{
		FundROIDBDAO fundRoiDbDao = new FundROIDBDAO();
		ArrayList<FundROI> roiList = fundRoiDbDao.getFundROIList();
		
		// convert to web response
	    JSONArray fundJsonList = new JSONArray();
		
		try
		{
			for(FundROI roi : roiList)
			{
				fundJsonList.put(JsonConverter.toJSON(roi));
			}
			
			// form html response msg
		    resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println(fundJsonList.toString());
			out.close();
		
		}
		catch (Exception e)
		{
			LoggerManager.getInstance().getLogger().error(e);
			e.printStackTrace();
		}
		
	}
}