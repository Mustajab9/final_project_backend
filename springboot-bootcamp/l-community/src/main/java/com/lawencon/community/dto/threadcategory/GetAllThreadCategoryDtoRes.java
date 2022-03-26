package com.lawencon.community.dto.threadcategory;

import java.util.List;

public class GetAllThreadCategoryDtoRes {
	private String msg;
	private List<GetAllThreadCategoryDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllThreadCategoryDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllThreadCategoryDtoDataRes> data) {
		this.data = data;
	}
}
