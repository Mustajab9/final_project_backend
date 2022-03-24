package com.lawencon.community.dto.regency;

import java.util.List;

public class GetAllRegencyDtoRes {
	private String msg;
	private List<GetAllRegencyDtoDataRes> data;

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
}
