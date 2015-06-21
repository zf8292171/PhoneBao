package com.webservice.dao;

import java.util.List;

import com.webservice.javabean.Comments;

public interface ShopCommentsDAO {
	public List<Comments> getShopComments(int id,int num) throws Exception;
}
