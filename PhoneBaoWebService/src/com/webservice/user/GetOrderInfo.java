package com.webservice.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.webservice.dao.impl.OrderImple;
import com.webservice.javabean.Order;
import com.webservice.javabean.ShopCartInfo;

public class GetOrderInfo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getShopCartInfo.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String strUserName = request.getParameter("userName");
		if (strUserName == null) {
			return;
		}
		
		OrderImple business = new OrderImple();
		List<Order> result = null;
		PrintWriter out = null;
		try {
			result = business.getOrderInfo(strUserName);
			JSONArray jsonResult = JSONArray.fromObject(result);
			out = response.getWriter();
			// ∑¢ÀÕ
			out.print(jsonResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
			out = response.getWriter();
			out.print("ªÒ»° ß∞‹");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
