package com.lawencon.community.dto.paymentmethod;

public class InsertPaymentMethodDtoRes {
	private String msg;
	private InsertPaymentMethodDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public InsertPaymentMethodDtoDataRes getData() {
		return data;
	}

	public void setData(InsertPaymentMethodDtoDataRes data) {
		this.data = data;
	}
}
