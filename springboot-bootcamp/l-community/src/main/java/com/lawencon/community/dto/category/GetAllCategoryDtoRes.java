package com.lawencon.community.dto.category;

import java.util.List;

public class GetAllCategoryDtoRes {
	private String msg;
	private List<GetAllCategoryDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllCategoryDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllCategoryDtoDataRes> data) {
		this.data = data;
	}
}
