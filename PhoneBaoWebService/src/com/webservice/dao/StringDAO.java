package com.webservice.dao;

import java.util.List;

import com.webservice.javabean.StringClass;

public interface StringDAO {
	public List<StringClass> getProvince() throws Exception ;
	
	public List<StringClass> getCity(String province) throws Exception ;
	
	public List<StringClass> getCountry(String city) throws Exception ;
}
