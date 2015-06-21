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

@WebServlet("/repeter.do")
public class UserRepeter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("repeter.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {

			String user = request.getParameter("user");

			if (user == null || user.equals("")) {
				return;
			}
			// 数据库判断
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			long count =  userDaoImpl.repeterCheck(user);

			out = response.getWriter();
			if (count > 0) {
				out.print("此账号已存在");
			} else {
				out.print("success");
			}

		} catch (Exception e) {
			e.printStackTrace();
			out = response.getWriter();
			out.print("服务端异常");
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}
}
