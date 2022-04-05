package com.lawencon.community.dto.threadtype;

import java.util.List;

public class GetAllThreadTypeDtoRes {
	private String msg;
	private List<GetAllThreadTypeDtoDataRes> data;
	private Integer total;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllThreadTypeDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllThreadTypeDtoDataRes> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
