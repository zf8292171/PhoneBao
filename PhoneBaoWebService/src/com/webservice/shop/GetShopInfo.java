package com.webservice.shop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.webservice.dao.impl.ShopInfoImpl;
import com.webservice.javabean.ShopInfo;

public class GetShopInfo extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("getShopHeadPic.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获取id值
		String strID = request.getParameter("id");
		if(strID == null){
			return ;
		}
		Integer id = Integer.valueOf(strID);
		ShopInfoImpl shopInfoImpl = new ShopInfoImpl();
		ShopInfo shopInfo = null;
		PrintWriter out = null;
		try {
			shopInfo = shopInfoImpl.getShopInfo(id);
			out = response.getWriter();
			JSONObject result = JSONObject.fromObject(shopInfo);
			out.print(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
			out = response.getWriter();
			out.print("读取错误");
			if(out != null){
				out.close();
			}
		}
	}

}
