package com.webservice.shop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.webservice.dao.impl.AddressImpl;
import com.webservice.dao.impl.ShopInfoImpl;
import com.webservice.utils.CustomerException;

public class InsertAddress extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("insertAddress.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String  para = request.getParameter("json");
		// 接受参数
		if(para==null){
			return ;
		}
		JSONObject json = JSONObject.fromObject(para);
		String name = json.getString("name");
		String phone = json.getString("phone");
		String street =json.getString("street");
		String country = json.getString("country");
		String userName = json.getString("userName");
		if (name == null || phone == null || street == null||country==null||userName==null) {
			return;
		}
		// 执行
		AddressImpl business = new AddressImpl();
		PrintWriter out = null;
		try {
			business.insertAddress(country, street, phone, name, userName);
			out = response.getWriter();

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
