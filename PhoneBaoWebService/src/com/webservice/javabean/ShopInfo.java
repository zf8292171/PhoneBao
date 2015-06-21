package com.webservice.javabean;

public class ShopInfo {

	private int id;
	private String name;
	private String no;
	private double price;
	private int goodsNums;
	private String introduce;
	private int sellNums;
	private String goodsContent;
	private String time;
	private String brand;
	private String cpu;
	private String phoneSize;
	private String os;
	private String ram;
	private String rom;
	private String resolution;
	private String preCamera;
	private String camera;
	private String color;
	private String commucation;

	public ShopInfo() {
		super();
	}

	public ShopInfo(int id, String name, String no, double price, int goodsNums, String introduce, int sellNums, String goodsContent,
			String time, String brand, String cpu, String phoneSize, String os, String ram, String rom, String resolution,
			String preCamera, String camera, String color, String commucation) {
		super();
		this.id = id;
		this.name = name;
		this.no = no;
		this.price = price;
		this.goodsNums = goodsNums;
		this.introduce = introduce;
		this.sellNums = sellNums;
		this.goodsContent = goodsContent;
		this.time = time;
		this.brand = brand;
		this.cpu = cpu;
		this.phoneSize = phoneSize;
		this.os = os;
		this.ram = ram;
		this.rom = rom;
		this.resolution = resolution;
		this.preCamera = preCamera;
		this.camera = camera;
		this.color = color;
		this.commucation = commucation;
	}

	@Override
	public String toString() {
		return "ShopInfo [id=" + id + ", name=" + name + ", no=" + no + ", price=" + price + ", goodsNums=" + goodsNums + ", introduce="
				+ introduce + ", sellNums=" + sellNums + ", goodsContent=" + goodsContent + ", time=" + time + ", brand=" + brand
				+ ", cpu=" + cpu + ", phoneSize=" + phoneSize + ", os=" + os + ", ram=" + ram + ", rom=" + rom + ", resolution="
				+ resolution + ", preCamera=" + preCamera + ", camera=" + camera + ", color=" + color + ", commucation=" + commucation
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getGoodsNums() {
		return goodsNums;
	}

	public void setGoodsNums(int goodsNums) {
		this.goodsNums = goodsNums;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getSellNums() {
		return sellNums;
	}

	public void setSellNums(int sellNums) {
		this.sellNums = sellNums;
	}

	public String getGoodsContent() {
		return goodsContent;
	}

	public void setGoodsContent(String goodsContent) {
		this.goodsContent = goodsContent;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getPhoneSize() {
		return phoneSize;
	}

	public void setPhoneSize(String phoneSize) {
		this.phoneSize = phoneSize;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getRom() {
		return rom;
	}

	public void setRom(String rom) {
		this.rom = rom;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getPreCamera() {
		return preCamera;
	}

	public void setPreCamera(String preCamera) {
		this.preCamera = preCamera;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCommucation() {
		return commucation;
	}

	public void setCommucation(String commucation) {
		this.commucation = commucation;
	}
}
