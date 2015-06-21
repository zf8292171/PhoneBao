package com.webservice.javabean;

public class Comments {

	private String user;
	private String content;
	private String time;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Comments [user=" + user + ", content=" + content + ", time=" + time + "]";
	}
	public Comments(String user, String content, String time) {
		super();
		this.user = user;
		this.content = content;
		this.time = time;
	}
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
