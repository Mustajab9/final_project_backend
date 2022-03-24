package com.lawencon.community.dto.event;

public class UpdateEventDtoRes {
	private String msg;
	private UpdateEventDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UpdateEventDtoDataRes getData() {
		return data;
	}

	public void setData(UpdateEventDtoDataRes data) {
		this.data = data;
	}
}
