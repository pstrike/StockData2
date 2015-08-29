package autodeal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Order {
	public ArrayList<DealBuy> getBuyOrder()
	{
		processCheckOrder();
		
		ArrayList<DealBuy> deals = new ArrayList<DealBuy>();
		DealBuy deal = null;
		
		String filePath = System.getProperty("user.dir")+"/autodeal/order.txt";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "gbk")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,
			String str = null;
			reader.readLine();
			while ((str = reader.readLine()) != null) {
				String item [] = str.split("\t");
				
				if(item[3].equals("买入"))
				{
					deal = new DealBuy();
					
					deal.setId(item[1]);
					deal.setPrice(Double.valueOf(item[4]));
					deal.setVolume(Integer.valueOf(item[5]));
					
					if(Integer.valueOf(item[7])>0)
					{
						deal.setDone(true);
					}
					else
					{
						deal.setDone(false);
					}
					
					deals.add(deal);
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return deals;
	}
	
	private void processCheckOrder()
	{
		Runtime rn = Runtime.getRuntime();
        try
        {
        	rn.exec(System.getProperty("user.dir")+"/autodeal/checkOrder.exe");
        	Thread.sleep(10000);
        }
        catch (Exception e)
        {
        	System.out.println("Error exec check order exe!");
        }
	}
}
