package com.lawencon.community.dto.enrollevent;

import java.util.List;

public class GetAllEnrollEventDtoRes {
	private String msg;
	private List<GetAllEnrollEventDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllEnrollEventDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllEnrollEventDtoDataRes> data) {
		this.data = data;
	}
}
