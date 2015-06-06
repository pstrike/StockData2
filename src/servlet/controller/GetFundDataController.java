package servlet.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Fund;
import model.FundROI;

import org.json.JSONArray;

import utility.json.JsonConverter;
import utility.log.LoggerManager;
import dao.FundDBDAO;
import dao.FundROIDBDAO;

public class GetFundDataController extends StockController
{
	@Override
	public void action(HttpServletRequest req, HttpServletResponse resp)
	{
		FundDBDAO fundDbDao = new FundDBDAO();
		FundROIDBDAO fundRoiDbDao = new FundROIDBDAO();
	    JSONArray fundJsonList = new JSONArray();
	    ArrayList<Fund> fundList = null;
	    ArrayList<FundROI> fundRoiList = null;
		
		try
		{
			fundList = fundDbDao.getLastestFundList();
			fundRoiList = fundRoiDbDao.getFundROIList();
			
			for(Fund fund : fundList)
			{
				boolean isUptrend = false;
				
				if(fund.getFundRate() > fund.getFundRateYesterday())
				{
					isUptrend = true;
				}
				
				for(FundROI fundRoi : fundRoiList)
				{
					if(fundRoi.getRateLower() <= fund.getFundRate() && fund.getFundRate() < fundRoi.getRateUpper() && ((fundRoi.isUptrend() && isUptrend)||(!fundRoi.isUptrend() && !isUptrend)))
					{
						if(fundRoi.getType().equals("A"))
						{
							fund.setFundHistoricalDealATxnNo(fundRoi.getDealTxnNo());
							fund.setFundHistoricalDealAWinPct(fundRoi.getWinPct());
							fund.setFundHistoricalDealAWinRoi(fundRoi.getRoi());
						}
						else if (fundRoi.getType().equals("B"))
						{
							fund.setFundHistoricalDealBTxnNo(fundRoi.getDealTxnNo());
							fund.setFundHistoricalDealBWinPct(fundRoi.getWinPct());
							fund.setFundHistoricalDealBWinRoi(fundRoi.getRoi());
						}
					}
				}
				
				fundJsonList.put(JsonConverter.toJSON(fund));
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
