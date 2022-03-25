package com.lawencon.community.dto.threadcategory;

import java.util.List;

public class GetThreadCategoryByThreadDtoRes {
	private String msg;
	private List<GetThreadCategoryByThreadDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetThreadCategoryByThreadDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetThreadCategoryByThreadDtoDataRes> data) {
		this.data = data;
	}
}
