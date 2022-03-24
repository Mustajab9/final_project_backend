package com.lawencon.community.dto.threadcomment;

public class GetByThreadCommentIdDtoRes {
	private String msg;
	private GetByThreadCommentIdDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GetByThreadCommentIdDtoDataRes getData() {
		return data;
	}

	public void setData(GetByThreadCommentIdDtoDataRes data) {
		this.data = data;
	}
}
