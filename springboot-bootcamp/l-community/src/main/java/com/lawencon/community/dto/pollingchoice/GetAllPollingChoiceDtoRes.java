package com.lawencon.community.dto.pollingchoice;

import java.util.List;

public class GetAllPollingChoiceDtoRes {
	private String msg;
	private List<GetAllPollingChoiceDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllPollingChoiceDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPollingChoiceDtoDataRes> data) {
		this.data = data;
	}
}
