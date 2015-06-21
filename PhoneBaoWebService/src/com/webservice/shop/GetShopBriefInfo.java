package com.webservice.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.webservice.dao.impl.ShopBriefImpl;
import com.webservice.javabean.ShopBriefInfo;
import com.webservice.javabean.ShopFilter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetShopBriefInfo extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getShopBreafInfo.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String str = request.getParameter("json");

		// 如若没有该类则退出
		if (str == null) {
			return;
		}

		// 将数据打包城json
		JSONObject json = JSONObject.fromObject(str);
		// 将数据解析成javabean
		ShopFilter shopFilter = new ShopFilter(json.getString("content"), json.getString("brand"), json.getString("cpu"),
				json.getString("ram"), json.getString("rom"), json.getString("px"), json.getString("color"), json.getString("web"),
				json.getString("price"));

		Integer order = json.getInt("order");
		Integer num = json.getInt("num");
		if(order == null || num == null){
			return ;
		}

		PrintWriter out = null;
		try {
			List<ShopBriefInfo> result = null;
			ShopBriefImpl shopBriefImpl = new ShopBriefImpl();
			result = shopBriefImpl.getShopBriefInfo(shopFilter,order,num);
			out = response.getWriter();
			// 将数据打包成jsonarray
			JSONArray jsonResult = JSONArray.fromObject(result);
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
