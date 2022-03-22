package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class Bookmark extends BaseEntity {
	
	private static final long serialVersionUID = -4436783267811824380L;
	private String bookmarCode;
	private Thread threadId;
	
	public String getBookmarCode() {
		return bookmarCode;
	}
	public void setBookmarCode(String bookmarCode) {
		this.bookmarCode = bookmarCode;
	}
	public Thread getThreadId() {
		return threadId;
	}
	public void setThreadId(Thread threadId) {
		this.threadId = threadId;
	}
	
	
}
