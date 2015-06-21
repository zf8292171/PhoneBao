package com.webservice.shop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webservice.dao.impl.PicPathImpl;
import com.webservice.dao.impl.ShopBriefImpl;
import com.webservice.javabean.PicPath;
import com.webservice.utils.Utils;

public class GetShopHeadPics extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getShopHeadPics.dopost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//��ȡidֵ
		String strID = request.getParameter("id");
		String strIndex = request.getParameter("index");
		if(strID == null ||strIndex ==null){
			return ;
		}
		Integer id = Integer.valueOf(strID);
		Integer index = Integer.valueOf(strIndex);
		
		PicPathImpl business = new PicPathImpl();
		File file = null;
		InputStream is = null;
		OutputStream out = null;
		try {
			//ͨ��id��ȡͼƬ�ļ���·��
			List<PicPath> path = business.getGoodsHeadPicPath(id);
			if(path.size()<=index){
				return ;
			}
			if(path.get(index).getPath()==null ||path.get(index).getPath().equals("")){
				return ;
			}
			file = new File(Utils.path+path.get(index).getPath());
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
			out.write("��ȡ����".getBytes());
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
