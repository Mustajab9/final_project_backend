package com.lawencon.community.dto.subscription;

public class GetSubscriptionByUserDtoRes {
	private String msg;
	private GetSubscriptionByUserDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GetSubscriptionByUserDtoDataRes getData() {
		return data;
	}

	public void setData(GetSubscriptionByUserDtoDataRes data) {
		this.data = data;
	}
}
