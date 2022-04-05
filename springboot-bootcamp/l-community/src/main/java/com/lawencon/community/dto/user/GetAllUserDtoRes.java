package com.lawencon.community.dto.user;

import java.util.List;

public class GetAllUserDtoRes {
	private String msg;
	private List<GetAllUserDtoDataRes> data;
	private Long total;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllUserDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllUserDtoDataRes> data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
