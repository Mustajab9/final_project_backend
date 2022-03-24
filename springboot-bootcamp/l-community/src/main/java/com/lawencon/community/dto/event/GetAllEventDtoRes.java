package com.lawencon.community.dto.event;

import java.util.List;

public class GetAllEventDtoRes {
	private String msg;
	private List<GetAllEventDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllEventDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllEventDtoDataRes> data) {
		this.data = data;
	}
}
