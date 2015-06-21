package com.webservice.dao.impl;

import java.util.List;

import com.webservice.dao.DAO;
import com.webservice.dao.OrderDAO;
import com.webservice.dao.StringDAO;
import com.webservice.javabean.Order;
import com.webservice.javabean.StringClass;

public class StringDAOImpl extends DAO<StringClass> implements StringDAO{

	@Override
	public List<StringClass> getProvince() throws Exception{
		return getForList("select province as str from dm_province", null);
	}

	@Override
	public List<StringClass> getCity(String province) throws Exception {
		int id  = getValue("select province_id from dm_province where province = ?", province);
		return getForList("select city as str from dm_city where province_id = "+id);
	}

	@Override
	public List<StringClass> getCountry(String city) throws Exception {
		int id  = getValue("select city_id from dm_city where city = ?", city);
		return getForList("select county as str from dm_county where city_id = "+id);
	}

}
