package autodeal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Balance {
	public ArrayList<Holding> getBalance()
	{
		processCheckBalance();
		
		ArrayList<Holding> holdings = new ArrayList<Holding>();
		Holding holding = null;
		
		String filePath = System.getProperty("user.dir")+"/autodeal/holding.txt";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "gbk")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,
			String str = null;
			reader.readLine();
			while ((str = reader.readLine()) != null) {
				String item [] = str.split("\t");
				holding = new Holding();
				
				holding.setId(item[0]);
				holding.setVolumeTotal(Integer.valueOf(item[2]));
				holding.setVolumeAvailable(Integer.valueOf(item[3]));
				holding.setVolumeFrozen(Integer.valueOf(item[4]));
				holding.setPriceCost(Double.valueOf(item[5]));
				holding.setPriceBalance(Double.valueOf(item[6]));
				holding.setPrice(Double.valueOf(item[7]));
				holding.setProfitPct(Double.valueOf(item[8]));
				holding.setProfitAmt(Double.valueOf(item[9]));
				
				holdings.add(holding);
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
		
		return holdings;
	}
	
	private void processCheckBalance()
	{
		Runtime rn = Runtime.getRuntime();
        try
        {
        	rn.exec(System.getProperty("user.dir")+"/autodeal/checkHolding.exe");
        	Thread.sleep(10000);
        }
        catch (Exception e)
        {
        	System.out.println("Error exec check holding exe!");
        }
	}
	
}
