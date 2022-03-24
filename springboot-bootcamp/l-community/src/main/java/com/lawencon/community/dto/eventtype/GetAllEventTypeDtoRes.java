package com.lawencon.community.dto.eventtype;

import java.util.List;

public class GetAllEventTypeDtoRes {
	private String msg;
	private List<GetAllEventTypeDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllEventTypeDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllEventTypeDtoDataRes> data) {
		this.data = data;
	}
}
