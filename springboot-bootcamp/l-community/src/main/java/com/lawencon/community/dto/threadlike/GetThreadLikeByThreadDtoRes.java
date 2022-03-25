package com.lawencon.community.dto.threadlike;

import java.util.List;

public class GetThreadLikeByThreadDtoRes {
	private String msg;
	private List<GetThreadLikeByThreadDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetThreadLikeByThreadDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetThreadLikeByThreadDtoDataRes> data) {
		this.data = data;
	}
}
