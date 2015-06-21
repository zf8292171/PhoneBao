package com.webservice.dao;

import java.util.Map;

public interface ShopOperateDAO {
	
	public void playOrder(String userName, Map<Integer, Integer> info,int addressID) throws Exception;
	public void collect(String userName,int goodsID) throws Exception;
	public void disCollect(String userName,int goodsID) throws Exception;
	public boolean isCollect(String userName,int goodsID) throws Exception;
	public void orderCancle(String userName, Map<Integer, Integer> info)  throws Exception;
}
