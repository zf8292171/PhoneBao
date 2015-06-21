package com.webservice.javabean;

public class ID {
	Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ID [id=" + id + "]";
	}

	public ID(Integer id) {
		super();
		this.id = id;
	}

	public ID() {
		super();
	}
	
}
