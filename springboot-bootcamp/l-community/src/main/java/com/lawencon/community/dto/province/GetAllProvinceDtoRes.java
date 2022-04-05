package com.lawencon.community.dto.province;

import java.util.List;

public class GetAllProvinceDtoRes {
	private String msg;
	private List<GetAllProvinceDtoDataRes> data;
	private Long total;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllProvinceDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllProvinceDtoDataRes> data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
