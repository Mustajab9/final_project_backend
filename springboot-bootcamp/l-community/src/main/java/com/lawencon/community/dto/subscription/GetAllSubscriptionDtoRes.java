package com.lawencon.community.dto.subscription;

public class GetAllSubscriptionDtoRes {
	private String msg;
	private GetAllSubscriptionDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GetAllSubscriptionDtoDataRes getData() {
		return data;
	}

	public void setData(GetAllSubscriptionDtoDataRes data) {
		this.data = data;
	}
}
