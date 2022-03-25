package com.lawencon.community.dto.pollingchoice;

import java.util.List;

public class GetByPollingIdDtoRes {
	private String msg;
	private List<GetByPollingIdDtoDataRes> data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<GetByPollingIdDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetByPollingIdDtoDataRes> data) {
		this.data = data;
	}
	
	
}
