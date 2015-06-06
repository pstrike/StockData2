package model;

import java.util.ArrayList;
import java.util.Date;

public class StockLedgerTxn {
	
	/* ledger structure
	 * Debit A/C: Cash, Balance, Investment 
	 * Credit A/C: Budget, Return
	 */
	public final static String ASSET_CASH_ACCOUNT = "cash";
	public final static String ASSET_INVESTMENT_ACCOUNT = "investment";
	public final static String ASSET_BALANCE_ACCOUNT = "balance";
	public final static String OE_BUDGET_ACCOUNT = "budget";
	public final static String OE_RETURN_ACCOUNT = "return";
	public final static double OE_BUDGET_INIT_AMT = 9999999999.0;
	
	private Date txnDate;
	private String txnDebitAccount;
	private String txnCreditAccount;
	private double txnAmount;
	
	public StockLedgerTxn(Date txnDate, String txnDebitAccount, String txnCreditAccount, double txnAmount)
	{
		this.txnDate = txnDate;
		this.txnDebitAccount = txnDebitAccount;
		this.txnCreditAccount = txnCreditAccount;
		this.txnAmount = txnAmount;
	}
	
	public Date getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}
	public String getTxnDebitAccount() {
		return txnDebitAccount;
	}
	public void setTxnDebitAccount(String txnDebitAccount) {
		this.txnDebitAccount = txnDebitAccount;
	}
	public String getTxnCreditAccount() {
		return txnCreditAccount;
	}
	public void setTxnCreditAccount(String txnCreditAccount) {
		this.txnCreditAccount = txnCreditAccount;
	}
	public double getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(double txnAmount) {
		this.txnAmount = txnAmount;
	}
	
	public static double getAccountBalance(ArrayList<StockLedgerTxn> ledgerTxnList, String account)
	{
		double result = 0;
		
		if(account.equals(ASSET_CASH_ACCOUNT)||account.equals(ASSET_INVESTMENT_ACCOUNT)||account.equals(ASSET_BALANCE_ACCOUNT))
		{
			for(StockLedgerTxn tempTxn : ledgerTxnList)
			{
				if(tempTxn.getTxnDebitAccount().equals(account))
				{
					result -= tempTxn.getTxnAmount();
				}
				
				if(tempTxn.getTxnCreditAccount().equals(account))
				{
					result += tempTxn.getTxnAmount();
				}
			}
		}
		
		if(account.equals(OE_BUDGET_ACCOUNT)||account.equals(OE_RETURN_ACCOUNT))
		{
			for(StockLedgerTxn tempTxn : ledgerTxnList)
			{
				if(tempTxn.getTxnDebitAccount().equals(account))
				{
					result += tempTxn.getTxnAmount();
				}
				
				if(tempTxn.getTxnCreditAccount().equals(account))
				{
					result -= tempTxn.getTxnAmount();
				}
			}
		}
		
		return result;
	}
	
	public static ArrayList <StockLedgerTxn> subTotalTxnList (ArrayList <StockLedgerTxn> txnList, Date txnDate)
	{
		ArrayList <StockLedgerTxn> result = new ArrayList <StockLedgerTxn>();
		
		String acList [][] = {
				{ASSET_CASH_ACCOUNT, ASSET_INVESTMENT_ACCOUNT},
				{ASSET_CASH_ACCOUNT, ASSET_BALANCE_ACCOUNT},
				{ASSET_CASH_ACCOUNT, OE_BUDGET_ACCOUNT},
				{ASSET_CASH_ACCOUNT, OE_RETURN_ACCOUNT},
				{ASSET_INVESTMENT_ACCOUNT, ASSET_CASH_ACCOUNT},
				{ASSET_BALANCE_ACCOUNT, ASSET_CASH_ACCOUNT},
				{OE_BUDGET_ACCOUNT, ASSET_CASH_ACCOUNT},
				{OE_RETURN_ACCOUNT, ASSET_CASH_ACCOUNT},
				
				{ASSET_INVESTMENT_ACCOUNT, ASSET_CASH_ACCOUNT},
				{ASSET_INVESTMENT_ACCOUNT, ASSET_BALANCE_ACCOUNT},
				{ASSET_INVESTMENT_ACCOUNT, OE_BUDGET_ACCOUNT},
				{ASSET_INVESTMENT_ACCOUNT, OE_RETURN_ACCOUNT},
				{ASSET_CASH_ACCOUNT, ASSET_INVESTMENT_ACCOUNT},
				{ASSET_BALANCE_ACCOUNT, ASSET_INVESTMENT_ACCOUNT},
				{OE_BUDGET_ACCOUNT, ASSET_INVESTMENT_ACCOUNT},
				{OE_RETURN_ACCOUNT, ASSET_INVESTMENT_ACCOUNT},
				
				{ASSET_BALANCE_ACCOUNT, ASSET_CASH_ACCOUNT},
				{ASSET_BALANCE_ACCOUNT, ASSET_INVESTMENT_ACCOUNT},
				{ASSET_BALANCE_ACCOUNT, OE_BUDGET_ACCOUNT},
				{ASSET_BALANCE_ACCOUNT, OE_RETURN_ACCOUNT},
				{ASSET_CASH_ACCOUNT, ASSET_BALANCE_ACCOUNT},
				{ASSET_INVESTMENT_ACCOUNT, ASSET_BALANCE_ACCOUNT},
				{OE_BUDGET_ACCOUNT, ASSET_BALANCE_ACCOUNT},
				{OE_RETURN_ACCOUNT, ASSET_BALANCE_ACCOUNT},
				
				{OE_BUDGET_ACCOUNT, ASSET_INVESTMENT_ACCOUNT},
				{OE_BUDGET_ACCOUNT, ASSET_BALANCE_ACCOUNT},
				{OE_BUDGET_ACCOUNT, ASSET_CASH_ACCOUNT},
				{OE_BUDGET_ACCOUNT, OE_RETURN_ACCOUNT},
				{ASSET_INVESTMENT_ACCOUNT, OE_BUDGET_ACCOUNT},
				{ASSET_BALANCE_ACCOUNT, OE_BUDGET_ACCOUNT},
				{ASSET_CASH_ACCOUNT, OE_BUDGET_ACCOUNT},
				{OE_RETURN_ACCOUNT, OE_BUDGET_ACCOUNT},
				
				{OE_RETURN_ACCOUNT, ASSET_INVESTMENT_ACCOUNT},
				{OE_RETURN_ACCOUNT, ASSET_BALANCE_ACCOUNT},
				{OE_RETURN_ACCOUNT, OE_BUDGET_ACCOUNT},
				{OE_RETURN_ACCOUNT, ASSET_CASH_ACCOUNT},
				{ASSET_INVESTMENT_ACCOUNT, OE_RETURN_ACCOUNT},
				{ASSET_BALANCE_ACCOUNT, OE_RETURN_ACCOUNT},
				{OE_BUDGET_ACCOUNT, OE_RETURN_ACCOUNT},
				{ASSET_CASH_ACCOUNT, OE_RETURN_ACCOUNT}
		};
		
		// init sub total txn
		for(int i=0; i<acList.length; i++)
		{
			result.add(new StockLedgerTxn(txnDate,acList[i][0],acList[i][1],0));
		}
		
		// calculate sub total txn
		for(StockLedgerTxn txn : txnList)
		{
			for(StockLedgerTxn subTotalTxn : result)
			{
				if(txn.getTxnDebitAccount().equals(subTotalTxn.getTxnDebitAccount()) 
						&& txn.getTxnCreditAccount().equals(subTotalTxn.getTxnCreditAccount()))
				{
					subTotalTxn.setTxnAmount(subTotalTxn.getTxnAmount() + txn.getTxnAmount());
					break;
				}
			}
			
		}
		
		/*
		for(StockLedgerTxn subTotalTxn : result)
		{
			if(subTotalTxn.getTxnAmount()>0)
				System.out.println("Date:"+txnDate+";Txn No:"+txnList.size()+";Debit:"+subTotalTxn.getTxnDebitAccount()+";Credit:"+subTotalTxn.getTxnCreditAccount()+";Amt:"+subTotalTxn.getTxnAmount());
		}
		*/
		
		return result;
	}
}
