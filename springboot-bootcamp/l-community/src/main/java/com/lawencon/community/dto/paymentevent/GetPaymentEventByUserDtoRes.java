package com.lawencon.community.dto.paymentevent;

import java.util.List;

public class GetPaymentEventByUserDtoRes {
	private String msg;
	private List<GetPaymentEventByUserDtoDataRes> data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<GetPaymentEventByUserDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetPaymentEventByUserDtoDataRes> data) {
		this.data = data;
	}
	
	
}
