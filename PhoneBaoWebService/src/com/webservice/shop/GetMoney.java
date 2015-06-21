package com.webservice.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.webservice.dao.impl.StringDAOImpl;
import com.webservice.dao.impl.UserDaoImpl;
import com.webservice.javabean.StringClass;

public class GetMoney extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getMoney.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		if(userName==null){
			return ;
		}
		
		PrintWriter out = null;
		try {
			String result ;
			UserDaoImpl business = new UserDaoImpl();
			result = String.valueOf(business.getMoney(userName));
			out = response.getWriter();
			out.print(result);
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
