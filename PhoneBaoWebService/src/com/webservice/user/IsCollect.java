package com.webservice.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webservice.dao.impl.ShopOperateImpl;
import com.webservice.javabean.Address;

public class IsCollect extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("isCollect.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		

		String userName = request.getParameter("userName");
		String strGoodsID = request.getParameter("goodsID");
		if(userName == null|| strGoodsID==null){
			return;
		}
		Integer goodsID = Integer.valueOf(strGoodsID);
		ShopOperateImpl business = new ShopOperateImpl();
		PrintWriter out = null;
		try {
			List<Address> result = new ArrayList<Address>();
			
			boolean tag = business.isCollect(userName, goodsID);
			
			out = response.getWriter();
			if(tag==true)
				out.print("true");
			else
				out.print("false");
			
		} catch (Exception e) {
			e.printStackTrace();
			out = response.getWriter();
			out.print("failed");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
