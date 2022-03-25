package com.lawencon.community.dto.subscriptiondetail;

import java.util.List;

public class GetBySubscriptionIdDtoRes {
	private String msg;
	private List<GetBySubscriptionIdDtoDataRes> data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<GetBySubscriptionIdDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetBySubscriptionIdDtoDataRes> data) {
		this.data = data;
	}
	
	
}
