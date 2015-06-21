package com.webservice.javabean;

public class ShopFilter {
	private String content ;
	private String brand;
	private String cpu;
	private String ram;
	private String rom;
	private String px;
	private String color;
	private String web;
	private String price;
	{
		content = "";
		brand = "全部";
		cpu = "全部";
		ram = "全部";
		rom = "全部";
		px = "全部";
		color = "全部";
		web = "全部";
		price = "全部";
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getPx() {
		return px;
	}
	public void setPx(String px) {
		this.px = px;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ShopFilter [content=" + content + ", brand=" + brand + ", cpu=" + cpu + ", ram=" + ram + ", rom=" + rom + ", px=" + px
				+ ", color=" + color + ", web=" + web + ", price=" + price + "]";
	}
	public ShopFilter(String content, String brand, String cpu, String ram, String rom, String px, String color, String web, String price) {
		super();
		this.content = content;
		this.brand = brand;
		this.cpu = cpu;
		this.ram = ram;
		this.rom = rom;
		this.px = px;
		this.color = color;
		this.web = web;
		this.price = price;
	}
	public ShopFilter() {
		super();
	}
	
}
