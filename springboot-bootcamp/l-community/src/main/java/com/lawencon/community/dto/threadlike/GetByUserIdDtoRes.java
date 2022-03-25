package com.lawencon.community.dto.threadlike;

import java.util.List;

public class GetByUserIdDtoRes {
	private String msg;
	private List<GetByUserIdDtoDataRes> data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<GetByUserIdDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetByUserIdDtoDataRes> data) {
		this.data = data;
	}
	
}
