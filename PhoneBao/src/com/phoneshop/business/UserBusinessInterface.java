package com.phoneshop.business;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;

import com.phoneshop.javabean.Order;

public interface UserBusinessInterface {
	public void login(String user, String password) throws ConnectTimeoutException, ConnectException, SocketTimeoutException, Exception;

	public void userRepeter(String user) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;

	public void userRegeist(Map<String, String> data) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;

	public void getOrderInfo(String userName,List<Order> data) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;

	public void deleteAdress(int id) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;

	public void getProvice(List<String> data) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;
	
	public void getCity(List<String> data,String province) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;

	public void getCountry(List<String> data,String city) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;

	public void insertAddress(String province,String city,String country,String name,String phone,String address,String userName) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;

	public Double getMoney(String userName) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;
}


