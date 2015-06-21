package com.webservice.dao.impl;

import java.util.List;

import com.webservice.dao.DAO;
import com.webservice.dao.ShopInfoDAO;
import com.webservice.javabean.Comments;
import com.webservice.javabean.ShopInfo;
import com.webservice.utils.CustomerException;

public class ShopInfoImpl extends DAO<ShopInfo> implements ShopInfoDAO {

	@Override
	public ShopInfo getShopInfo(int id) throws Exception {
		ShopInfo shopInfo = null;
		String sql = "select goods_id as id,goods_name as name,goods_no as no,goods_price as price,"
				+ "goods_num as goodsNums,goods_introduce as introduce,goods_sell_num as sellNums,"
				+ "goods_content as goodsContent,goods_appeared_time as time,phonebrand as brand,"
				+ "cpu as cpu,phonesize as phoneSize,operatingsystem as os,ram as ram,rom as rom,resolution as resolution,"
				+ "pre_camera as preCamera ,camera as camera,phonecolor as color,communication as commucation from goods_view"
				+ " where goods_id=" + id;

		shopInfo = get(sql, null);

		return shopInfo;
	}

	@Override
	public String getShopPic(int id, int index) throws Exception {
		String sql = "";
		switch (index) {
		case 0:
			sql = "select goods_pic1 from goods_view where goods_id = " + id;
			break;
		case 1:
			sql = "select goods_pic2 from goods_view where goods_id = " + id;
			break;
		case 2:
			sql = "select goods_pic3 from goods_view where goods_id = " + id;
			break;
		case 3:
			sql = "select goods_pic4 from goods_view where goods_id = " + id;
			break;
		case 4:
			sql = "select goods_pic5 from goods_view where goods_id = " + id;
			break;

		}
		return String.valueOf(getValue(sql, null));
	}

	@Override
	public void addToShopCard(int goodsID, String user, int num) throws CustomerException,Exception {
		
		//获取用户id
		Long userID = 0L;
		userID = getValue("select user_id from t_bao_users where user_name = ?", user);
		//获取用户id在购物车是否有了该商品
		Long has = getValue("select count(*) from t_shopping_cart where user_id = "+userID+" and goods_id = "+goodsID, null);
		if(has>0){
			throw new CustomerException("repeat");
		}
		//添加商品入购物车
		String nullString = "";
		String sql = "insert into t_shopping_cart(goods_id,user_id,shopping_cart_num) values(?,?,?)";
		update(sql, (Integer)goodsID,userID,1);
		
	}

	@Override
	public String getShopHeadPics(int id, int index) throws Exception {
		String sql = "";
		sql = "select goods_pic from t_goods_picture where goods_id = " + id+" limit 5";
		switch (index) {
		case 0:
			sql = "select goods_pic from t_goods_picture where goods_id = " + id;
			break;
		case 1:
			sql = "select goods_pic2 from goods_view where goods_id = " + id;
			break;
		case 2:
			sql = "select goods_pic3 from goods_view where goods_id = " + id;
			break;
		case 3:
			sql = "select goods_pic4 from goods_view where goods_id = " + id;
			break;
		case 4:
			sql = "select goods_pic5 from goods_view where goods_id = " + id;
			break;

		}
		return String.valueOf(getValue(sql, null));
	}





}
