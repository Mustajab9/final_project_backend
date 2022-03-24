package com.lawencon.community.dto.enrolldetail;

import java.util.List;

public class GetAllEnrollDetailDtoRes {
	private String msg;
	private List<GetAllEnrollDetailDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllEnrollDetailDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllEnrollDetailDtoDataRes> data) {
		this.data = data;
	}
}
