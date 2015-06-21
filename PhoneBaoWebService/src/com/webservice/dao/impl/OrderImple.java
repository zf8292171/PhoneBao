package com.webservice.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.webservice.dao.DAO;
import com.webservice.dao.OrderDAO;
import com.webservice.javabean.Order;

public class OrderImple extends DAO<Order> implements OrderDAO{

	@Override
	public List<Order> getOrderInfo(String userName) throws Exception {
		List<Order> result = null;
		// 获取用户id
		Long userID = 0L;
		userID = getValue("select user_id from t_bao_users where user_name = ?", userName);
		String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String sql = "select ordergoods_id as id,t_ordergoods.goods_id as goodsID,goods_name as goodsName,ordergoods_num as goodsNum,"+
				 "og_sum_price as totlePrice,deal_state as orderState,shop_name as shopName,order_time as time,"+
				" city,province,county as country,t_user_address.street,phone_number as phone,receiver as name"+
				" from t_ordergoods,t_goods,`dm_deal_state`,t_shop,dm_county,dm_city,`dm_province`,`t_user_address`"+
				" where t_ordergoods.`goods_id` = `t_goods`.`goods_id` and `t_ordergoods`.`deal_state_id` = `dm_deal_state`.`deal_state_id`"+
				"  and `t_goods`.`shop_id` = `t_shop`.`shop_id` and `t_ordergoods`.`address_id` = `t_user_address`.`address_id`"+
				"  and t_user_address.`county_id` = dm_county.`county_id` and dm_county.`city_id` = dm_city.`city_id`"+
				"  and dm_city.`province_id` = dm_province.`province_id` and TO_DAYS(?) - TO_DAYS(order_time)<=30 and t_ordergoods.user_id = "+userID+" order by time desc";
		
		
		result = getForList(sql, time);
		
		return result;
	}

}
