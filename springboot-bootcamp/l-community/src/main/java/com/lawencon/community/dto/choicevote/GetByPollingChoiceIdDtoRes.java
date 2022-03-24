package com.lawencon.community.dto.choicevote;

import java.util.List;

public class GetByPollingChoiceIdDtoRes {
	private String msg;
	private List<GetByPollingChoiceIdDtoDataRes> data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<GetByPollingChoiceIdDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetByPollingChoiceIdDtoDataRes> data) {
		this.data = data;
	}
	
	
}
