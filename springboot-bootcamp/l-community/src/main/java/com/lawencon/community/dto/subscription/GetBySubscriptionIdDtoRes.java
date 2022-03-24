package com.lawencon.community.dto.subscription;

public class GetBySubscriptionIdDtoRes {
	private String msg;
	private GetBySubscriptionIdDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GetBySubscriptionIdDtoDataRes getData() {
		return data;
	}

	public void setData(GetBySubscriptionIdDtoDataRes data) {
		this.data = data;
	}
}
