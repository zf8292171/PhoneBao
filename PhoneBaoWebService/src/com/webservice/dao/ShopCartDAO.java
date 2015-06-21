package com.webservice.dao;

import java.util.List;

import com.webservice.javabean.ShopCartInfo;
import com.webservice.utils.CustomerException;

public interface ShopCartDAO {
	public List<ShopCartInfo> getShopCartInfo(String userName) throws CustomerException , Exception;
}
