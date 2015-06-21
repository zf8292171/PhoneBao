package com.webservice.dao.impl;

import java.util.List;

import com.webservice.dao.DAO;
import com.webservice.dao.IDDAO;
import com.webservice.javabean.ID;

public class IDImpl extends DAO<ID> implements IDDAO{

	@Override
	public List<ID> getADIDs() throws Exception {
		List<ID> goodsID;
		String sql = "select goods_id as id from t_goods_ad";
		goodsID = getForList(sql, null);
		return goodsID;
	}

}
