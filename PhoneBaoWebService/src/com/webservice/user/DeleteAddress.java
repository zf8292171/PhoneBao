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
import com.webservice.dao.impl.UserDaoImpl;
import com.webservice.javabean.Order;

public class DeleteAddress extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteAddress.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String strUserName = request.getParameter("id");
		if (strUserName == null) {
			return;
		}
		Integer id = Integer.valueOf(strUserName);
		UserDaoImpl business = new UserDaoImpl();
		PrintWriter out = null;
		try {
			 business.deleteAddress(id);
			out = response.getWriter();
			// ·¢ËÍ
			out.print("success");
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
