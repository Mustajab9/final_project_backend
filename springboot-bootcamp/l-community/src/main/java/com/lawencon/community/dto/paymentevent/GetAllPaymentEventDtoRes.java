package com.lawencon.community.dto.paymentevent;

import java.util.List;

public class GetAllPaymentEventDtoRes {
	private String msg;
	private List<GetAllPaymentEventDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllPaymentEventDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPaymentEventDtoDataRes> data) {
		this.data = data;
	}
}
