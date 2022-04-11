package com.lawencon.community.service;

import com.lawencon.community.dto.pollingchoice.DeleteByPollingChoiceIdDtoRes;
import com.lawencon.community.dto.pollingchoice.GetAllPollingChoiceDtoRes;
import com.lawencon.community.dto.pollingchoice.GetByPollingChoiceIdDtoRes;
import com.lawencon.community.dto.pollingchoice.GetByPollingIdDtoRes;
import com.lawencon.community.dto.pollingchoice.InsertPollingChoiceDtoReq;
import com.lawencon.community.dto.pollingchoice.InsertPollingChoiceDtoRes;
import com.lawencon.community.dto.pollingchoice.UpdatePollingChoiceDtoReq;
import com.lawencon.community.dto.pollingchoice.UpdatePollingChoiceDtoRes;

public interface PollingChoiceService {
	GetAllPollingChoiceDtoRes findAll() throws Exception;
	GetByPollingChoiceIdDtoRes findById(String id) throws Exception;
	InsertPollingChoiceDtoRes insert(InsertPollingChoiceDtoReq data) throws Exception;
	UpdatePollingChoiceDtoRes update(UpdatePollingChoiceDtoReq data) throws Exception;
	DeleteByPollingChoiceIdDtoRes deleteById(String id) throws Exception;
	GetByPollingIdDtoRes findByPolling(String id) throws Exception;
}
