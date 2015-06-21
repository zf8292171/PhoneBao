package com.webservice.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.webservice.dao.impl.ShopOperateImpl;

public class OrderCancle extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("OrderCancle.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// String userName, Map<Integer, Integer> info, int addressID
		String userName = request.getParameter("userName");
		String json = request.getParameter("info");

		if (userName == null || json == null) {
			return;
		}
		// 解析info
		Map<Integer, Integer> info = new HashMap<Integer, Integer>();
		JSONArray array = JSONArray.fromObject(json);
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = array.getJSONObject(i);

			info.put(obj.getInt("goodsID"), obj.getInt("goodsNum"));

		}
		// 处理事件 订单提交成功反回sucess 否则反回failed
		ShopOperateImpl business = new ShopOperateImpl();
		PrintWriter out = null;
		try {
			business.orderCancle(userName, info);
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
