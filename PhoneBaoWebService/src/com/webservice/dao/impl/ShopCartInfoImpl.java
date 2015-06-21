package com.webservice.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.webservice.dao.DAO;
import com.webservice.dao.ShopCartDAO;
import com.webservice.javabean.ShopCartInfo;
import com.webservice.utils.CustomerException;

public class ShopCartInfoImpl extends DAO<ShopCartInfo> implements ShopCartDAO {

	@Override
	public List<ShopCartInfo> getShopCartInfo(String userName) throws CustomerException, Exception {

		// 获取用户id
		Long userID = 0L;
		userID = getValue("select user_id from t_bao_users where user_name = ?", userName);
		List<ShopCartInfo> result = null;
		String sql = "select shopping_cart_id as id,t_shopping_cart.goods_id as goodsID,goods_name as goodsName,"
				+ "shopping_cart_num as shopCartNum,goods_price as price,user_id as userID " + "from t_shopping_cart,t_goods "
				+ "where t_shopping_cart.goods_id = t_goods.goods_id and user_id = " + userID;
		result = getForList(sql, null);
		return result;
	}
}
