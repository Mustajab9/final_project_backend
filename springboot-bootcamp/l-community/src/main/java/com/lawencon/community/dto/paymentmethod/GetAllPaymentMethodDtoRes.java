package com.lawencon.community.dto.paymentmethod;

import java.util.List;

public class GetAllPaymentMethodDtoRes {
	private String msg;
	private List<GetAllPaymentMethodDtoDataRes> data;
	private Integer total;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllPaymentMethodDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPaymentMethodDtoDataRes> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
