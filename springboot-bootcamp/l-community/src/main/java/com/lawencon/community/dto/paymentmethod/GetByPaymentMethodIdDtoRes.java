package com.lawencon.community.dto.paymentmethod;

public class GetByPaymentMethodIdDtoRes {
	private String msg;
	private GetByPaymentMethodIdDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GetByPaymentMethodIdDtoDataRes getData() {
		return data;
	}

	public void setData(GetByPaymentMethodIdDtoDataRes data) {
		this.data = data;
	}
}
