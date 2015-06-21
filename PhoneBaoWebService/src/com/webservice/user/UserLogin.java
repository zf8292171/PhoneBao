package com.webservice.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webservice.dao.impl.UserDaoImpl;
import com.webservice.utils.CustomerException;

public class UserLogin extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			if (user == null || password == null) {
				return;
			}
			// 数据库判断
			UserDaoImpl tag = new UserDaoImpl();
			long count = tag.loginCheck(user, password);
			//

			out = response.getWriter();
			if (count > 0) {
				out.print("success");
			} else {
				out.print("账号密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out = response.getWriter();
			out.print("服务器异常问题");
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

}
