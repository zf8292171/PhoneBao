package com.webservice.dao;

import java.util.List;

import com.webservice.javabean.PicPath;

public interface PicPathDAO {

	public List<PicPath> getGoodsHeadPicPath(int goodsID) throws Exception;
	
	public List<PicPath> getAdPic(int goodsID) throws Exception;
}
