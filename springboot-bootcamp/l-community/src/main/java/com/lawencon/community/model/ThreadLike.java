package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class ThreadLike extends BaseEntity {
	
	private static final long serialVersionUID = 6705748480227802740L;
	private String likeCode;
	private Thread threadId;
	
	public String getLikeCode() {
		return likeCode;
	}
	public void setLikeCode(String likeCode) {
		this.likeCode = likeCode;
	}
	public Thread getThreadId() {
		return threadId;
	}
	public void setThreadId(Thread threadId) {
		this.threadId = threadId;
	}
	
	
}
