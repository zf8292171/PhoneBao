package com.webservice.javabean;


public class ShopBriefInfo {

	int id;
	String goodsName;
	double price;
	int sellNum;
	String province;
	String city;
	String shopName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSellNum() {
		return sellNum;
	}
	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	@Override
	public String toString() {
		return "ShopBriefInfo [id=" + id + ", goodsName=" + goodsName + ", price=" + price + ", sellNum=" + sellNum + ", province="
				+ province + ", city=" + city + ", shopName=" + shopName + "]";
	}
	public ShopBriefInfo(int id, String goodsName, double price, int sellNum, String province, String city, String shopName) {
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.price = price;
		this.sellNum = sellNum;
		this.province = province;
		this.city = city;
		this.shopName = shopName;
	}
	public ShopBriefInfo() {
		super();
	}
	
	
}
