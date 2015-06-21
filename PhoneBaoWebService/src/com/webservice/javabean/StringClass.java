package com.webservice.javabean;

public class StringClass {
	String str;

	public StringClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StringClass(String str) {
		super();
		this.str = str;
	}

	@Override
	public String toString() {
		return "StringClass [str=" + str + "]";
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
