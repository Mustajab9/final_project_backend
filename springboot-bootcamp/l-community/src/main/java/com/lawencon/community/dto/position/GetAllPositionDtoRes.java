package com.lawencon.community.dto.position;

import java.util.List;

public class GetAllPositionDtoRes {
	private String msg;
	private List<GetAllPositionDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllPositionDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPositionDtoDataRes> data) {
		this.data = data;
	}
}
