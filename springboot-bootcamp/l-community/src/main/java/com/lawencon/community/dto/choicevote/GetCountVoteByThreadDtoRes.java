package com.lawencon.community.dto.choicevote;

import java.util.List;

public class GetCountVoteByThreadDtoRes {
	private String msg;
	private List<GetCountVoteByThreadDtoDataRes> data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<GetCountVoteByThreadDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetCountVoteByThreadDtoDataRes> data) {
		this.data = data;
	}
	
	
}
