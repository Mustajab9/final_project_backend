package com.lawencon.community.dto.event;

import java.util.List;

public class GetEventByCategoryDtoRes {
	private String msg;
	private List<GetEventByCategoryDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetEventByCategoryDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetEventByCategoryDtoDataRes> data) {
		this.data = data;
	}

}
