package com.webservice.dao;

import java.util.List;

import com.webservice.javabean.ShopBriefInfo;
import com.webservice.javabean.ShopFilter;
import com.webservice.javabean.ShopInfo;

public interface ShopBriefDAO {
	public List<ShopBriefInfo> getShopBriefInfo (ShopFilter shopFilter,int order,int num) throws Exception;
	public String getShopHeadPic (int id) throws Exception;
	public List<ShopBriefInfo> getGoodsCollect(String userName) throws Exception;
}
