package com.lawencon.community.dto.paymentevent;

public class InsertPaymentEventDtoRes {
	private String msg;
	private InsertPaymentEventDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public InsertPaymentEventDtoDataRes getData() {
		return data;
	}

	public void setData(InsertPaymentEventDtoDataRes data) {
		this.data = data;
	}
}
