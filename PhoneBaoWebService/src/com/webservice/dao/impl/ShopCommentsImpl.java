package com.webservice.dao.impl;

import java.util.List;

import com.webservice.dao.DAO;
import com.webservice.dao.ShopCommentsDAO;
import com.webservice.javabean.Comments;

public class ShopCommentsImpl extends DAO<Comments> implements ShopCommentsDAO {

	@Override
	public List<Comments> getShopComments(int id, int num) throws Exception {
		List<Comments> result = null;
		String sql = "select user_name as user,recommend_time as time,judge_content as content from v_comments where goods_id = "
				+ id + " limit " + num;
		result = getForList(sql);
		return result;
	}

}
