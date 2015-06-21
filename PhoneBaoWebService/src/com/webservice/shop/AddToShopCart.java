package com.webservice.shop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.webservice.dao.impl.ShopInfoImpl;
import com.webservice.utils.CustomerException;

public class AddToShopCart extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getShopBreafInfo.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 接受参数

		String strGoodsID = request.getParameter("goodsID");
		String strUserName = request.getParameter("userName");
		String strNum = request.getParameter("goodsNum");
		if (strGoodsID == null || strUserName == null || strNum == null) {
			return;
		}
		Integer goodsID = Integer.valueOf(strGoodsID);
		Integer num = Integer.valueOf(strNum);
		// 执行
		ShopInfoImpl business = new ShopInfoImpl();
		PrintWriter out = null;
		try {
			business.addToShopCard(goodsID, strUserName, num);
			out = response.getWriter();

			out.print("success");
		} catch (CustomerException e) {
			out = response.getWriter();

			out.print("您已添加该商品");
		} catch (Exception e) {
			e.printStackTrace();
			out = response.getWriter();
			out.print("添加失败");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
