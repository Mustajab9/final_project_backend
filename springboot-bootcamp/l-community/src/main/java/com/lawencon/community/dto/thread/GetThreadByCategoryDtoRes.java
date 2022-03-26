package com.lawencon.community.dto.thread;

import java.util.List;

public class GetThreadByCategoryDtoRes {
	private String msg;
	private List<GetThreadByCategoryDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetThreadByCategoryDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetThreadByCategoryDtoDataRes> data) {
		this.data = data;
	}
}
