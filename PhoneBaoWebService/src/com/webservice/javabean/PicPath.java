package com.webservice.javabean;

public class PicPath {
	String goodsId;
	String path;
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public PicPath(String goodsId, String path) {
		super();
		this.goodsId = goodsId;
		this.path = path;
	}
	public PicPath() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PicPath [goodsId=" + goodsId + ", path=" + path + "]";
	}
	

}
