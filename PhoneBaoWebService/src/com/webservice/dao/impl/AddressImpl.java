package com.webservice.dao.impl;

import java.util.List;

import com.webservice.dao.AddressDAO;
import com.webservice.dao.DAO;
import com.webservice.javabean.Address;

public class AddressImpl extends DAO<Address> implements AddressDAO {

	@Override
	public List<Address> getAddressInfo(String userName) throws Exception {
		List<Address> result = null;

		// 获取用户id
		Long userID = 0L;
		userID = getValue("select user_id from t_bao_users where user_name = ?", userName);

		String sql = "select address_id as id,county as country," + "province as province,city as city,street,"
				+ "phone_number as phone,receiver as name " + "from t_user_address,dm_county,dm_city,dm_province "
				+ "where t_user_address.county_id = dm_county.county_id and "
				+ "dm_county.city_id = dm_city.city_id and dm_city.province_id =dm_province.province_id " + "and user_id =" + userID;

		result = getForList(sql, null);
		return result;

	}

	@Override
	public void insertAddress(String country, String street, String phone, String name, String userName) throws Exception {
		// 获取用户id
		Long userID = 0L;
		userID = getValue("select user_id from t_bao_users where user_name = ?", userName);
		
		int id  = getValue("select county_id from dm_county where county = ?", country);
		
		update("insert into t_user_address(user_id,county_id,street,phone_number,receiver) values(?,?,?,?,?)", userID,id,street,phone,name);
	}

}
