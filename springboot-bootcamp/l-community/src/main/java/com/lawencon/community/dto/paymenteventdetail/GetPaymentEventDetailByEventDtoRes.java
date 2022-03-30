package com.lawencon.community.dto.paymenteventdetail;

import java.util.List;

public class GetPaymentEventDetailByEventDtoRes {
	private String msg;
	private List<GetPaymentEventDetailByEventDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetPaymentEventDetailByEventDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetPaymentEventDetailByEventDtoDataRes> data) {
		this.data = data;
	}

}
