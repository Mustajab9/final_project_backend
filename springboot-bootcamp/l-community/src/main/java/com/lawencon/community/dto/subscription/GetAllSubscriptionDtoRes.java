package com.lawencon.community.dto.subscription;

import java.util.List;

public class GetAllSubscriptionDtoRes {
	private String msg;
	private List<GetAllSubscriptionDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllSubscriptionDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllSubscriptionDtoDataRes> data) {
		this.data = data;
	}
}
