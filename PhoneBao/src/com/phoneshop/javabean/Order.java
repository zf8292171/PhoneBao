package com.phoneshop.javabean;

public class Order {

	private int id;
	private int goodsID;
	private String goodsName;
	private int goodsNum;
	private double totlePrice;
	private String orderState;
	private String shopName;
	private String time;
	private String city;
	private String country;
	private String province;
	private String street;
	private String name;
	private String phone;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}
	public double getTotlePrice() {
		return totlePrice;
	}
	public void setTotlePrice(double totlePrice) {
		this.totlePrice = totlePrice;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Order(int id, int goodsID, String goodsName, int goodsNum, double totlePrice, String orderState, String shopName, String time,
			String city, String country, String province, String street, String name, String phone) {
		super();
		this.id = id;
		this.goodsID = goodsID;
		this.goodsName = goodsName;
		this.goodsNum = goodsNum;
		this.totlePrice = totlePrice;
		this.orderState = orderState;
		this.shopName = shopName;
		this.time = time;
		this.city = city;
		this.country = country;
		this.province = province;
		this.street = street;
		this.name = name;
		this.phone = phone;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", goodsID=" + goodsID + ", goodsName=" + goodsName + ", goodsNum=" + goodsNum + ", totlePrice="
				+ totlePrice + ", orderState=" + orderState + ", shopName=" + shopName + ", time=" + time + ", city=" + city + ", country="
				+ country + ", province=" + province + ", street=" + street + ", name=" + name + ", phone=" + phone + "]";
	}
	
	
	
}
