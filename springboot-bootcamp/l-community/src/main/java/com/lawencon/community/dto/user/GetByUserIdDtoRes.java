package com.lawencon.community.dto.user;

public class GetByUserIdDtoRes {
	private String msg;
	private GetByUserIdDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GetByUserIdDtoDataRes getData() {
		return data;
	}

	public void setData(GetByUserIdDtoDataRes data) {
		this.data = data;
	}
}
