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
import com.webservice.javabean.StringClass;

public class GetCity extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getCity.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String province = request.getParameter("province");
		if(province==null){
			return ;
		}
		
		PrintWriter out = null;
		try {
			List<StringClass> result ;
			StringDAOImpl business = new StringDAOImpl();
			result = business.getCity(province);
			out = response.getWriter();
			// �����ݴ����jsonarray
			JSONArray jsonResult = JSONArray.fromObject(result);
			System.out.println(result);
			// ����
			out.print(jsonResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
			out = response.getWriter();
			out.print("��ȡʧ��");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
