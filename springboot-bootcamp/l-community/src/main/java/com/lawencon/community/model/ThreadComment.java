package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class ThreadComment extends BaseEntity {
	
	private static final long serialVersionUID = -8641242134161390310L;
	private String commentContent;
	private String commentCode;
	private Thread threadId;
	
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentCode() {
		return commentCode;
	}
	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}
	public Thread getThreadId() {
		return threadId;
	}
	public void setThreadId(Thread threadId) {
		this.threadId = threadId;
	}
	
	
	
}
