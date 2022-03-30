package com.lawencon.community.dto.paymenteventdetail;

import java.util.List;

public class GetAllPaymentEventDetailDtoRes {
	private String msg;
	private List<GetAllPaymentEventDetailDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllPaymentEventDetailDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPaymentEventDetailDtoDataRes> data) {
		this.data = data;
	}
}
