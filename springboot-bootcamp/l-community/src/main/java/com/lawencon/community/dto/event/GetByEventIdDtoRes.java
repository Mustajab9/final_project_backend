package com.lawencon.community.dto.event;

public class GetByEventIdDtoRes {
	private String msg;
	private GetByEventIdDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GetByEventIdDtoDataRes getData() {
		return data;
	}

	public void setData(GetByEventIdDtoDataRes data) {
		this.data = data;
	}
}
