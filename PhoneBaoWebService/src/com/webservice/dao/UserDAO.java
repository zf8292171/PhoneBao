package com.webservice.dao;

import java.util.List;

import com.webservice.javabean.ShopBriefInfo;
import com.webservice.javabean.ShopFilter;
import com.webservice.javabean.UserInfoBean;
import com.webservice.utils.CustomerException;

public interface UserDAO {
	public long loginCheck(String userName,String password) throws Exception ;
	public void userRegeist(String userName,String password,String phone,String email) throws Exception;
	public long repeterCheck(String userName) throws Exception  ;
	public void deleteAddress(int id) throws Exception;
	public Double getMoney(String userName) throws Exception;
}
