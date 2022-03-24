package com.lawencon.community.dto.subscriptiondetail;

import java.util.List;

public class GetAllSubscriptionDetailDtoRes {
	private String msg;
	private List<GetAllSubscriptionDetailDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllSubscriptionDetailDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllSubscriptionDetailDtoDataRes> data) {
		this.data = data;
	}
}
