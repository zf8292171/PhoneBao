package com.webservice.shop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webservice.dao.impl.ShopBriefImpl;
import com.webservice.utils.Utils;

public class GetShopHeadPic extends HttpServlet {
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
		
		ShopBriefImpl shopBriefImpl = new ShopBriefImpl();
		File file = null;
		InputStream is = null;
		OutputStream out = null;
		try {
			//通过id获取图片文件的路劲
			String path = shopBriefImpl.getShopHeadPic(id);
			file = new File(Utils.path+path);
			is = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] b = new byte[100];
			int length= 0 ;
			while((length = is.read(b))!=-1){
				out.write(b, 0, length);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			out = response.getOutputStream();
			out.write("读取错误".getBytes());
		}finally{
			if(is!=null){
				is.close();
			}
			if(out!=null){
				out.close();
			}
		}
	}

}
