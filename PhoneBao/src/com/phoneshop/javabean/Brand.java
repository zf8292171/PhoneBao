package com.phoneshop.javabean;

public class Brand {
	
	private int imgID ;
	private String brandName ;
	public int getImgID() {
		return imgID;
	}
	public void setImgID(int imgID) {
		this.imgID = imgID;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	@Override
	public String toString() {
		return "Brand [imgID=" + imgID + ", brandName=" + brandName + "]";
	}
	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Brand(int imgID, String brandName) {
		super();
		this.imgID = imgID;
		this.brandName = brandName;
	}
	
}
