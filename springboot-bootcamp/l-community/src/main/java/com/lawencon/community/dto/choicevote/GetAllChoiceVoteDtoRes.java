package com.lawencon.community.dto.choicevote;

import java.util.List;

public class GetAllChoiceVoteDtoRes {
	private String msg;
	private List<GetAllChoiceVoteDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllChoiceVoteDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllChoiceVoteDtoDataRes> data) {
		this.data = data;
	}
}
