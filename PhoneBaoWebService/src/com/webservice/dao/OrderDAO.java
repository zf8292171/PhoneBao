package com.webservice.dao;

import java.util.List;

import com.webservice.javabean.Order;

public interface OrderDAO {
	public List<Order> getOrderInfo(String userName) throws Exception;
}
