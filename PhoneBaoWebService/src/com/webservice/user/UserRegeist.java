package com.webservice.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webservice.dao.impl.UserDaoImpl;
import com.webservice.utils.CustomerException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserRegeist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		System.out.println("regeist.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			String strJson = request.getParameter("json");

			if (strJson == null || strJson.equals("")) {
				return;
			}

			JSONObject params = JSONObject.fromObject(strJson);
			String user, password, phone, email;
			user = params.getString("user");
			password = params.getString("password");
			phone = params.getString("phone");
			email = params.getString("email");

			if (user == null || user.equals("") || password == null
					|| password.equals("") || phone == null || phone.equals("")
					|| email == null || email.equals("")) {
				return;
			}
			//
			// System.out.println("user-->"+user);
			// System.out.println("password-->"+password);
			// System.out.println("name-->"+phone);
			// System.out.println("no-->"+email);

			// 数据库注册事件。。。

			UserDaoImpl userDaoImpl = new UserDaoImpl();
			userDaoImpl.userRegeist(user, password, phone, email);
			out = response.getWriter();
			out.print("success");
		} catch (Exception e) {
			e.printStackTrace();
			out = response.getWriter();
			out.print("注册失败");
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}
}
