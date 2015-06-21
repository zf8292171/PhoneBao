package com.webservice.dao.impl;

import java.util.List;

import com.webservice.dao.DAO;
import com.webservice.dao.UserDAO;
import com.webservice.javabean.ShopBriefInfo;
import com.webservice.javabean.ShopFilter;
import com.webservice.javabean.UserInfoBean;
import com.webservice.utils.CustomerException;

public class UserDaoImpl extends DAO<UserInfoBean> implements UserDAO {

	@Override
	public long loginCheck(String userName, String password) throws Exception {
		String sql = "select count(*) from t_bao_users where user_name = ? and user_password = MD5(?)";
		try {
			long count = getValue(sql, userName, password);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("UserDAoImpLoginCheck");
		}
	}

	@Override
	public void userRegeist(String userName, String password, String phone, String email) throws Exception {
		String sql = "insert into t_bao_users(user_name,user_password,user_phone_number,user_email) values(?,MD5(?),?,?)";
		try {
			update(sql, userName, password, phone, email);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("UserDAoImpuserRegeist");
		}
	}

	@Override
	public long repeterCheck(String userName) throws Exception {
		try {
			String sql = "select count(*) from t_bao_users where user_name = ? ";
			return getValue(sql, userName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("UserDAoImpRepeterCheck");
		}
	}

	@Override
	public void deleteAddress(int id) throws Exception {
		String sql = "delete from t_user_address where address_id = " + id;
		update(sql, null);
	}

	@Override
	public Double getMoney(String userName) throws Exception {
		// 获取用户id
		Long userID = 0L;
		userID = getValue("select user_id from t_bao_users where user_name = ?", userName);
		
		return getValue("select balance from t_bao_users where user_id = "+userID);
	}

}
