package com.lawencon.community.dto.paymentevent;

public class GetByPaymentEventIdDtoRes {
	private String msg;
	private GetByPaymentEventIdDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GetByPaymentEventIdDtoDataRes getData() {
		return data;
	}

	public void setData(GetByPaymentEventIdDtoDataRes data) {
		this.data = data;
	}
}
