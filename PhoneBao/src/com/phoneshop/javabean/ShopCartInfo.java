package com.phoneshop.javabean;

public class ShopCartInfo {
	int id;
	int goodsID;
	int userID;
	String goodsName;
	int shopCartNum;
	double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getShopCartNum() {
		return shopCartNum;
	}
	public void setShopCartNum(int shopCartNum) {
		this.shopCartNum = shopCartNum;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ShopCartInfo [id=" + id + ", goodsID=" + goodsID + ", userID=" + userID + ", goodsName=" + goodsName + ", shopCartNum="
				+ shopCartNum + ", price=" + price + "]";
	}
	public ShopCartInfo(int id, int goodsID, int userID, String goodsName, int shopCartNum, double price) {
		super();
		this.id = id;
		this.goodsID = goodsID;
		this.userID = userID;
		this.goodsName = goodsName;
		this.shopCartNum = shopCartNum;
		this.price = price;
	}
	public ShopCartInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
