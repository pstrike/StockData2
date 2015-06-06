package servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class StockController {
	public abstract void action(HttpServletRequest req, HttpServletResponse resp);
}
