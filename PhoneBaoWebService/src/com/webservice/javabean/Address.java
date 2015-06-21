package com.webservice.javabean;

import java.io.Serializable;


public class Address   {
	
	int id;
	String province;
	String city;
	String country;
	String street;
	String phone;
	String name;
	

	public Address(int id, String province, String city, String country, String street, String phone, String name) {
		super();
		this.id = id;
		this.province = province;
		this.city = city;
		this.country = country;
		this.street = street;
		this.phone = phone;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

}
