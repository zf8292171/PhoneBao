package com.webservice.dao.impl;

import java.util.List;

import com.webservice.dao.DAO;
import com.webservice.dao.PicPathDAO;
import com.webservice.javabean.PicPath;

public class PicPathImpl extends DAO<PicPath> implements PicPathDAO{

	@Override
	public List<PicPath> getGoodsHeadPicPath(int  goodsID) throws Exception {
		List<PicPath> result = null;
		String sql = "select goods_pic as path  from t_goods_picture where goods_id =  "+goodsID;
		result = getForList(sql, null);
		return result;
	}

	@Override
	public List<PicPath> getAdPic(int goodsID) throws Exception {
		List<PicPath> result = null;
		String sql = "select goods_ad_picture as path  from t_goods_ad where goods_id =  "+goodsID;
		result = getForList(sql, null);
		return result;
	}

}
