package controller;

import java.util.ArrayList;
import java.util.Date;

import dao.StockDBDAO;
import dao.StockDealDBDAO;
import dao.StockROIDBDAO;
import utility.date.DateUtil;
import utility.log.LoggerManager;
import model.Stock;
import model.StockDeal;
import model.StockLedgerTxn;
import model.StockROI;

public class FullStockROICalculationController extends Controller {

	@Override
	public void action() 
	{
		StockDealDBDAO stockDealDbDao = new StockDealDBDAO();
		StockDBDAO stockDbDao = new StockDBDAO();
		StockROIDBDAO stockRoiDao = new StockROIDBDAO();
		ArrayList<StockROI> roiList = generateROIList();
		ArrayList<StockDeal> dealList = null;
		ArrayList<StockLedgerTxn> ledgerTxnList = null;
		Date processDate = null;
		Date startDate = null;
		Date endDate = null;
		double balance = 0;
		Stock stock = null;
		int roiCounter=0;
		int processNo = 100;
		double restInvestmentAmt = 0;
		double restReturnAmt = 0;
		
		// remove all roi from db
		stockRoiDao.removeAllStockROI();
		
		// iterate all roi
		for(StockROI roi : roiList)
		{
			balance = 0;
			restInvestmentAmt = 0;
			restReturnAmt = 0;
			
			ledgerTxnList = new ArrayList<StockLedgerTxn>();
			ledgerTxnList.add(new StockLedgerTxn(roi.getStartDate(),
													StockLedgerTxn.OE_BUDGET_ACCOUNT,
													StockLedgerTxn.ASSET_CASH_ACCOUNT,
													StockLedgerTxn.OE_BUDGET_INIT_AMT));
			
			endDate = DateUtil.addDay(roi.getStartDate(),-1);
			
			while(endDate.before(roi.getEndDate()))
			{
				// init process date period
				startDate = DateUtil.addDay(endDate, 1);
				if(DateUtil.addDay(startDate, processNo).after(roi.getEndDate()))
				{
					endDate = roi.getEndDate();
				}
				else
				{
					endDate = DateUtil.addDay(startDate, processNo);
				}
				
				// sub total last iteration ledger txn
				ledgerTxnList = StockLedgerTxn.subTotalTxnList(ledgerTxnList, startDate);
				
				dealList = stockDealDbDao.getStockDealBetweenDate(startDate, endDate, roi.getStartDate(), roi.getEndDate());
				
				processDate = startDate;
				
				// iterate each date in process batch duration
				while(processDate.before(DateUtil.addDay(endDate,1)))
				{
					// locate deal match the date and process
					for(StockDeal deal : dealList)
					{
						// handle sell deal
						if(DateUtil.format(deal.getSellDate()).equals(DateUtil.format(processDate)))
						{
							// process investment back to balance
							ledgerTxnList.add(new StockLedgerTxn(processDate,
									StockLedgerTxn.ASSET_INVESTMENT_ACCOUNT,
									StockLedgerTxn.ASSET_BALANCE_ACCOUNT,
									deal.getBuyPrice()*deal.getVolume()));
							balance += deal.getBuyPrice()*deal.getVolume();
							
							// process return back to balance
							/*
							ledgerTxnList.add(new StockLedgerTxn(processDate,
									StockLedgerTxn.OE_RETURN_ACCOUNT,
									StockLedgerTxn.ASSET_BALANCE_ACCOUNT,
									(deal.getSellVar()-deal.getBuyVar())*deal.getBuyPrice()*deal.getVolume()));
							balance += (deal.getSellVar()-deal.getBuyVar())*deal.getBuyPrice()*deal.getVolume();
							*/
							ledgerTxnList.add(new StockLedgerTxn(processDate,
									StockLedgerTxn.OE_RETURN_ACCOUNT,
									StockLedgerTxn.ASSET_BALANCE_ACCOUNT,
									(deal.getSellPrice() - deal.getBuyPrice())*deal.getVolume()));
							balance += (deal.getSellPrice() - deal.getBuyPrice())*deal.getVolume();
							
							//System.out.println("["+processDate+"] "+"sell return amt: "+StockLedgerTxn.getAccountBalance(ledgerTxnList, StockLedgerTxn.OE_RETURN_ACCOUNT));
							
							if(deal.getSellType() == StockDeal.GOOD_SELL)
								roi.setWinTxnNo(roi.getWinTxnNo()+1);
							else if(deal.getSellType() == StockDeal.BAD_SELL)
								roi.setLoseTxnNo(roi.getLoseTxnNo()+1);
							else if(deal.getSellType() == StockDeal.LONG_SELL)
								roi.setLongTxnNo(roi.getLongTxnNo()+1);
							
							roi.setSellTxnNo(roi.getSellTxnNo()+1);
						}
						
						// handle buy deal
						if(DateUtil.format(deal.getBuyDate()).equals(DateUtil.format(processDate)))
						{
							if(balance < deal.getVolume()*deal.getBuyPrice())
							{
								ledgerTxnList.add(new StockLedgerTxn(processDate,
										StockLedgerTxn.ASSET_CASH_ACCOUNT,
										StockLedgerTxn.ASSET_BALANCE_ACCOUNT,
										deal.getVolume()*deal.getBuyPrice() - balance));
								balance += deal.getVolume()*deal.getBuyPrice() - balance;
							}
							
							ledgerTxnList.add(new StockLedgerTxn(processDate,
									StockLedgerTxn.ASSET_BALANCE_ACCOUNT,
									StockLedgerTxn.ASSET_INVESTMENT_ACCOUNT,
									deal.getVolume()*deal.getBuyPrice()));
							balance -= deal.getVolume()*deal.getBuyPrice();
							
							roi.setTotalTxnNo(roi.getTotalTxnNo()+1);
						}
					}
					
					processDate = DateUtil.addDay(processDate, 1);
				}
				
				// handle rest deal
				for(StockDeal deal : dealList)
				{
					if(!deal.isComplete() || deal.getSellDate().after(roi.getEndDate()))
					{
						stock = stockDbDao.getStockBaseOnStockIdAndDate(deal.getStockId(), roi.getEndDate());
						
						restInvestmentAmt += deal.getVolume()*deal.getBuyPrice();
						restReturnAmt += (stock.getStockAvg() - deal.getBuyPrice())*deal.getVolume();
						
						roi.setRestTxnNo(roi.getRestTxnNo()+1);
						roi.setRestAmt(restInvestmentAmt+restReturnAmt);
						
						if(restReturnAmt > 0)
						{
							roi.setWinTxnNo(roi.getWinTxnNo()+1);
						}
						else
						{
							roi.setLoseTxnNo(roi.getLoseTxnNo()+1);
						}
						
						//System.out.println(roi.getRestTxnNo()+": "+restInvestmentAmt);
					}
				}
			}
			
			// handle rest txn amt
			ledgerTxnList.add(new StockLedgerTxn(roi.getEndDate(),
					StockLedgerTxn.ASSET_INVESTMENT_ACCOUNT,
					StockLedgerTxn.ASSET_BALANCE_ACCOUNT,
					restInvestmentAmt));
			balance += restInvestmentAmt;
			
			ledgerTxnList.add(new StockLedgerTxn(roi.getEndDate(),
					StockLedgerTxn.OE_RETURN_ACCOUNT,
					StockLedgerTxn.ASSET_BALANCE_ACCOUNT,
					//deal.getVolume()*deal.getBuyPrice()*(stock.getStockPriceCostVar()-deal.getBuyVar())));
					restReturnAmt));
			balance += restReturnAmt;
			
			//System.out.println("["+roi.getEndDate()+"] "+"rest return amt: "+StockLedgerTxn.getAccountBalance(ledgerTxnList, StockLedgerTxn.OE_RETURN_ACCOUNT));
			
			roi.setBuyAmt(StockLedgerTxn.OE_BUDGET_INIT_AMT - StockLedgerTxn.getAccountBalance(ledgerTxnList, StockLedgerTxn.ASSET_CASH_ACCOUNT));
			roi.setSellAmt(StockLedgerTxn.getAccountBalance(ledgerTxnList, StockLedgerTxn.OE_RETURN_ACCOUNT)+roi.getBuyAmt());
			if(roi.getBuyAmt() > 0)
				roi.setRoi(roi.getSellAmt()/roi.getBuyAmt());
			else
				roi.setRoi(0);
			
			stockRoiDao.insertROI(roi);
			
			roiCounter++;
			LoggerManager.getInstance().getLogger().info("Processed ROI:"+roi.getStockId()+"; pending: "+(roiList.size()-roiCounter));
		}
	}
	
	public ArrayList<StockROI> generateROIList()
	{
		// init
		ArrayList<StockROI> result = new ArrayList<StockROI>();
		StockDBDAO stockDbDao = new StockDBDAO();
		StockROI roi = null;
		Date today = null;
		
		try
		{
			today = DateUtil.parse(DateUtil.getToday());
			
			// today
			roi = new StockROI();
			roi.setStartDate(today);
			roi.setEndDate(today);
			roi.setStockId("ld0");
			roi.setType(StockROI.TYPE_OVERALL);
			result.add(roi);
			
			// last week
			roi = new StockROI();
			roi.setStartDate(DateUtil.addDay(today, -6));
			roi.setEndDate(today);
			roi.setStockId("ld7");
			roi.setType(StockROI.TYPE_OVERALL);
			result.add(roi);
			
			// last month
			roi = new StockROI();
			roi.setStartDate(DateUtil.addDay(today, -29));
			roi.setEndDate(today);
			roi.setStockId("ld30");
			roi.setType(StockROI.TYPE_OVERALL);
			result.add(roi);
			
			// last quarter
			roi = new StockROI();
			roi.setStartDate(DateUtil.addDay(today, -89));
			roi.setEndDate(today);
			roi.setStockId("ld90");
			roi.setType(StockROI.TYPE_OVERALL);
			result.add(roi);
			
			// last half year
			roi = new StockROI();
			roi.setStartDate(DateUtil.addDay(today, -179));
			roi.setEndDate(today);
			roi.setStockId("ld180");
			roi.setType(StockROI.TYPE_OVERALL);
			result.add(roi);
			
			// last years
			int earliestYear = Integer.valueOf(DateUtil.format(stockDbDao.getEarliestStockDate(), "yyyy"));
			int thisYear = Integer.valueOf(DateUtil.format(today, "yyyy"));
			int counter = 1;
			
			for(int i = earliestYear; i<=thisYear; i++)
			{
				// last years till today
				roi = new StockROI();
				roi.setStartDate(DateUtil.addDay(today, -364 * counter));
				roi.setEndDate(today);
				roi.setStockId("ly"+counter);
				roi.setType(StockROI.TYPE_OVERALL);
				result.add(roi);
				
				// each last year
				roi = new StockROI();
				roi.setStartDate(DateUtil.getDate(String.valueOf(i), "01", "01"));
				roi.setEndDate(DateUtil.getDate(String.valueOf(i), "12", "31"));
				roi.setStockId("y"+i);
				roi.setType(StockROI.TYPE_OVERALL);
				result.add(roi);
				
				/*
				// each last year quarter
				for(int j = 0; j<4; j++)
				{
					roi = new StockROI();
					if(j*3+1>10)
						roi.setStartDate(DateUtil.getDate(String.valueOf(i), String.valueOf(j*3+1), "01"));
					else
						roi.setStartDate(DateUtil.getDate(String.valueOf(i), "0"+String.valueOf(j*3+1), "01"));
					
					if(j*3+3>10)
						roi.setEndDate(DateUtil.getDate(String.valueOf(i), String.valueOf(j*3+3), DateUtil.getLastDayOfMonth(String.valueOf(i), String.valueOf(j*3+3))));
					else
						roi.setEndDate(DateUtil.getDate(String.valueOf(i), "0"+String.valueOf(j*3+3), DateUtil.getLastDayOfMonth(String.valueOf(i), String.valueOf(j*3+3))));
					
					roi.setStockId("y"+i+"q"+(j+1));
					roi.setType(StockROI.TYPE_OVERALL);
					
					result.add(roi);
				}
				*/
				
				/*
				// each last year month
				for(int j = 0; j<12; j++)
				{
					roi = new StockROI();
					if(j+1>10)
					{
						roi.setStartDate(DateUtil.getDate(String.valueOf(i), String.valueOf(j+1), "01"));
						roi.setEndDate(DateUtil.getDate(String.valueOf(i), String.valueOf(j+1), DateUtil.getLastDayOfMonth(String.valueOf(i), String.valueOf(j+1))));
					}
					else
					{
						roi.setStartDate(DateUtil.getDate(String.valueOf(i), "0"+String.valueOf(j+1), "01"));
						roi.setEndDate(DateUtil.getDate(String.valueOf(i), "0"+String.valueOf(j+1), DateUtil.getLastDayOfMonth(String.valueOf(i), String.valueOf(j+1))));
					}
					
					roi.setStockId("y"+i+"m"+(j+1));
					roi.setType(StockROI.TYPE_OVERALL);
					
					result.add(roi);
				}
				*/
				
				counter++;
			}
		}
		catch (Exception ex)
		{
			LoggerManager.getInstance().getLogger().error("Generte ROI List Expcetion");
			LoggerManager.getInstance().getLogger().error(ex);
			ex.printStackTrace();
		}
	
		return result;
	}

}
