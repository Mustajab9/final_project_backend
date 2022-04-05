package com.lawencon.community.dto.eventtype;

import java.util.List;

public class GetAllEventTypeDtoRes {
	private String msg;
	private List<GetAllEventTypeDtoDataRes> data;
	private Long total;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllEventTypeDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllEventTypeDtoDataRes> data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
