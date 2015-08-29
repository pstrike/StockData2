package autodeal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Seller {
	public void sell(ArrayList<DealSell> deals){
		writeSellOrder(deals);
		processSellOrder();
	}
	
	private void writeSellOrder(ArrayList <DealSell> deals)
	{
		BufferedWriter fw = null;
		try {
			File file = new File(System.getProperty("user.dir")+"/autodeal/sellOrder.txt");
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "gbk")); // 指定编码格式，以免读取时中文字符异常
			
			for(int i=0;i<deals.size();i++)
			{
				DealSell deal = deals.get(i);
				
				if(i==0)
				{
					fw.append(deal.toString());
				}
				else
				{
					fw.newLine();
					fw.append(deal.toString());
				}
			}
			
			fw.flush(); // 全部写入缓存中的内容
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void processSellOrder()
	{
		Runtime rn = Runtime.getRuntime();
        try
        {
        	rn.exec(System.getProperty("user.dir")+"/autodeal/sellOrder.exe");
        }
        catch (Exception e)
        {
        	System.out.println("Error exec sell exe!");
        }
	}

}
