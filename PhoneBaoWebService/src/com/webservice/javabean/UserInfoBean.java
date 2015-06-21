package com.webservice.javabean;

/**
 * @author fan
 * 
 */
public class UserInfoBean {
	private int id;//主键唯一标识
	private String userName;//用户名
	private String phone ;
	private String email;
	private String name;//用户姓名
	private String sex;
	private Double balance;//用户余额
	private int isSeller;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public int getIsSeller() {
		return isSeller;
	}
	public void setIsSeller(int isSeller) {
		this.isSeller = isSeller;
	}
	@Override
	public String toString() {
		return "UserInfoBean [id=" + id + ", userName=" + userName + ", phone="
				+ phone + ", email=" + email + ", name=" + name + ", sex="
				+ sex + ", balance=" + balance + ", isSeller=" + isSeller + "]";
	}
	public UserInfoBean(int id, String userName, String phone, String email,
			String name, String sex, Double balance, int isSeller) {
		super();
		this.id = id;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.name = name;
		this.sex = sex;
		this.balance = balance;
		this.isSeller = isSeller;
	}
	public UserInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}