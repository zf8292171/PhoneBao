package com.webservice.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.webservice.dao.StringDAO;
import com.webservice.dao.impl.IDImpl;
import com.webservice.dao.impl.StringDAOImpl;
import com.webservice.javabean.ID;
import com.webservice.javabean.StringClass;

public class GetProvince extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getProvince.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = null;
		try {
			List<StringClass> result ;
			StringDAOImpl business = new StringDAOImpl();
			result = business.getProvince();
			out = response.getWriter();
			// 将数据打包成jsonarray
			JSONArray jsonResult = JSONArray.fromObject(result);
			System.out.println(result);
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
