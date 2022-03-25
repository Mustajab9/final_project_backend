package com.lawencon.community.dto.subscriptiondetail;

import java.util.List;

public class GetSubscriptionDetailBySubscriptionDtoRes {
	private String msg;
	private List<GetSubscriptionDetailBySubscriptionDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetSubscriptionDetailBySubscriptionDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetSubscriptionDetailBySubscriptionDtoDataRes> data) {
		this.data = data;
	}
}
