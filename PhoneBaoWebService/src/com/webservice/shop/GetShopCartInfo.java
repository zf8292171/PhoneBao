package com.webservice.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.webservice.dao.impl.ShopCartInfoImpl;
import com.webservice.javabean.ShopCartInfo;
import com.webservice.utils.CustomerException;

public class GetShopCartInfo extends HttpServlet {

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

		ShopCartInfoImpl business = new ShopCartInfoImpl();
		List<ShopCartInfo> result = null;
		PrintWriter out = null;
		try {
			//事物DAO
			result = business.getShopCartInfo(strUserName);
			JSONArray jsonResult = JSONArray.fromObject(result);
			out = response.getWriter();
			// 发送
			out.print(jsonResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
			out = response.getWriter();
			out.print("获取失败");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
