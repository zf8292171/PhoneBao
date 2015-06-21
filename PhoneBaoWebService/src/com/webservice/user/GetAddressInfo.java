package com.webservice.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.webservice.dao.impl.AddressImpl;
import com.webservice.javabean.Address;

public class GetAddressInfo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getAddressInfo.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("userName");
		if(userName == null){
			return;
		}
		
		AddressImpl business = new AddressImpl();
		PrintWriter out = null;
		try {
			List<Address> result = new ArrayList<Address>();
			
			result = business.getAddressInfo(userName);
			
			JSONArray arrayResult = JSONArray.fromObject(result);
			out = response.getWriter();
			out.print(arrayResult.toString());
			
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
