package com.lawencon.community.dto.threadattachment;

import java.util.List;

public class GetByThreadIdDtoRes {
	private String msg;
	private List<GetByThreadIdDtoDataRes> data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<GetByThreadIdDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetByThreadIdDtoDataRes> data) {
		this.data = data;
	}
	
	
	
}
