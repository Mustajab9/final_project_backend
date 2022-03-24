package com.lawencon.community.dto.threadcomment;

import java.util.List;

public class GetAllThreadCommentDtoRes {
	private String msg;
	private List<GetAllThreadCommentDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllThreadCommentDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllThreadCommentDtoDataRes> data) {
		this.data = data;
	}
}
