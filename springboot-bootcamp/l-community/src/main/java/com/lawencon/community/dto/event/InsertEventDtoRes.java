package com.lawencon.community.dto.event;

public class InsertEventDtoRes {
	private String msg;
	private InsertEventDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public InsertEventDtoDataRes getData() {
		return data;
	}

	public void setData(InsertEventDtoDataRes data) {
		this.data = data;
	}
}
