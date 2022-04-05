package com.lawencon.community.dto.regency;

import java.util.List;

public class GetAllRegencyDtoRes {
	private String msg;
	private List<GetAllRegencyDtoDataRes> data;
	private Long total;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllRegencyDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllRegencyDtoDataRes> data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
