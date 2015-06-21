package com.webservice.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.webservice.dao.impl.ShopBriefImpl;
import com.webservice.javabean.ShopBriefInfo;

public class GetGoodsCollect extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getGoodsCollect.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String str = request.getParameter("userName");

		// 如若没有该类则退出
		if (str == null) {
			return;
		}
		
		PrintWriter out = null;
		try {
			List<ShopBriefInfo> result = null;
			ShopBriefImpl shopBriefImpl = new ShopBriefImpl();
			result = shopBriefImpl.getGoodsCollect(str);
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
