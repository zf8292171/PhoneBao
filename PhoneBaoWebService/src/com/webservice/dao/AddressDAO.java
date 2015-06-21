package com.webservice.dao;

import java.util.List;

import com.webservice.javabean.Address;

public interface AddressDAO {
	public List<Address> getAddressInfo(String userName) throws Exception;

	public void insertAddress(String country,String street ,String phone,String name,String userName) throws Exception;
}
