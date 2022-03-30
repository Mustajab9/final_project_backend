package com.lawencon.community.dto.enrollevent;

import java.util.List;

public class GetEnrollEventByUserDtoRes {
	private String msg;
	private List<GetEnrollEventByUserDtoDataRes> data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<GetEnrollEventByUserDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetEnrollEventByUserDtoDataRes> data) {
		this.data = data;
	}
	
	
}
