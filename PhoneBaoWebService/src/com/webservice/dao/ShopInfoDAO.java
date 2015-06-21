package com.webservice.dao;

import java.util.List;

import com.webservice.javabean.Comments;
import com.webservice.javabean.ShopInfo;
import com.webservice.utils.CustomerException;

public interface ShopInfoDAO {

	public ShopInfo getShopInfo(int id) throws Exception;
	public String getShopPic(int id,int index) throws Exception;
	public void addToShopCard(int goodsID,String user , int num) throws CustomerException ,Exception;
	public String getShopHeadPics(int id,int index) throws Exception;
}
