package com.lawencon.community.dto.threadcomment;

import java.util.List;

public class GetThreadCommentByThreadDtoRes {
	private String msg;
	private List<GetThreadCommentByThreadDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetThreadCommentByThreadDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetThreadCommentByThreadDtoDataRes> data) {
		this.data = data;
	}
}
