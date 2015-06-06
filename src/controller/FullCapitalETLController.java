package controller;

import java.util.ArrayList;

import model.Capital;
import dao.CapitalCSVDAO;
import dao.CapitalDBDAO;

public class FullCapitalETLController extends Controller {

	@Override
	public void action()
	{
		// initialization
		CapitalCSVDAO capitalCsvDao = new CapitalCSVDAO();
		CapitalDBDAO capitalDBDao = new CapitalDBDAO();
		ArrayList<Capital> capitals = null;
		
		// extraction
		capitals = capitalCsvDao.getCapitals();
		
		// load
		for(Capital capital : capitals)
		{
			capitalDBDao.insertCapital(capital);
		}
	}

}
