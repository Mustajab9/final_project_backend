package com.lawencon.community.dto.threadcomment;

public class InsertThreadCommentDtoReq {
	private String commentContent;
	private String threadId;

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
}
