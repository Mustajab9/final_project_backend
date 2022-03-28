package com.lawencon.community.dto.profilesosmed;

import java.util.List;

public class GetProfileSosmedByUserDtoRes {
	private String msg;
	private List<GetProfileSosmedByUserDtoDataRes> data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<GetProfileSosmedByUserDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetProfileSosmedByUserDtoDataRes> data) {
		this.data = data;
	}
	
	
}
