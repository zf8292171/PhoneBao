package com.webservice.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.webservice.dao.impl.ShopCommentsImpl;
import com.webservice.javabean.Comments;
import com.webservice.javabean.ShopBriefInfo;

public class GetShopComments extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getShopComments.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String strId = request.getParameter("id");
		String strNum = request.getParameter("num");
		if (strId == null || strNum == null) {
			return;
		}
		Integer id = Integer.valueOf(strId);
		Integer num = Integer.valueOf(strNum);
		ShopCommentsImpl business = new ShopCommentsImpl();
		PrintWriter out = null;
		try {
			List<Comments> result = null;
			result = business.getShopComments(id, num);
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
