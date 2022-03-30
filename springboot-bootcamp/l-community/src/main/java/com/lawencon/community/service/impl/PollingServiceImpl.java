package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.PollingDao;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dto.polling.DeleteByPollingIdDtoRes;
import com.lawencon.community.dto.polling.GetAllPollingDtoDataRes;
import com.lawencon.community.dto.polling.GetAllPollingDtoRes;
import com.lawencon.community.dto.polling.GetByPollingIdDtoDataRes;
import com.lawencon.community.dto.polling.GetByPollingIdDtoRes;
import com.lawencon.community.dto.polling.GetPollingByThreadIdDtoDataRes;
import com.lawencon.community.dto.polling.GetPollingByThreadIdDtoRes;
import com.lawencon.community.dto.polling.InsertPollingDtoDataRes;
import com.lawencon.community.dto.polling.InsertPollingDtoReq;
import com.lawencon.community.dto.polling.InsertPollingDtoRes;
import com.lawencon.community.dto.pollingchoice.InsertPollingChoiceDtoReq;
import com.lawencon.community.model.Polling;
import com.lawencon.community.model.Thread;
import com.lawencon.community.service.PollingChoiceService;
import com.lawencon.community.service.PollingService;

@Service
public class PollingServiceImpl extends BaseService implements PollingService {
	private PollingDao pollingDao;
	private ThreadDao threadDao;
	private PollingChoiceService pollingChoiceService;

	@Autowired
	public PollingServiceImpl(PollingDao pollingDao, ThreadDao threadDao) {
		this.pollingDao = pollingDao;
		this.threadDao = threadDao;
	}
	
	@Autowired
	public void setPollingChoiceService(PollingChoiceService pollingChoiceService) {
		this.pollingChoiceService = pollingChoiceService;
	}
	
	@Override
	public GetAllPollingDtoRes findAll() throws Exception {
		GetAllPollingDtoRes getAll = new GetAllPollingDtoRes();

		List<Polling> pollings = pollingDao.findAll();
		List<GetAllPollingDtoDataRes> listPolling = new ArrayList<>();

		for (int i = 0; i < pollings.size(); i++) {
			Polling polling = pollings.get(i);
			GetAllPollingDtoDataRes data = new GetAllPollingDtoDataRes();

			data.setId(polling.getId());
			data.setPollingName(polling.getPollingName());
			data.setPollingCode(polling.getPollingCode());
			data.setThreadId(polling.getThreadId().getId());
			data.setThreadTitle(polling.getThreadId().getThreadTitle());
			data.setThreadContent(polling.getThreadId().getThreadContent());
			data.setVersion(polling.getVersion());
			data.setIsActive(polling.getIsActive());

			listPolling.add(data);
		}

		getAll.setData(listPolling);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByPollingIdDtoRes findById(String id) throws Exception {
		GetByPollingIdDtoRes getById = new GetByPollingIdDtoRes();

		Polling polling = pollingDao.findById(id);
		GetByPollingIdDtoDataRes data = new GetByPollingIdDtoDataRes();

		data.setId(polling.getId());
		data.setPollingName(polling.getPollingName());
		data.setPollingCode(polling.getPollingCode());
		data.setThreadId(polling.getThreadId().getId());
		data.setThreadTitle(polling.getThreadId().getThreadTitle());
		data.setThreadContent(polling.getThreadId().getThreadContent());
		data.setVersion(polling.getVersion());
		data.setIsActive(polling.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertPollingDtoRes insert(InsertPollingDtoReq data) throws Exception {
		InsertPollingDtoRes insert = new InsertPollingDtoRes();

		try {
			Polling polling = new Polling();
			String code = getAlphaNumericString(5);
			polling.setPollingCode(code);
			polling.setPollingName(data.getPollingName());
			
			Thread thread = threadDao.findById(data.getThreadId());
			polling.setThreadId(thread);
			polling.setCreatedBy(getId());

			begin();
			Polling pollingInsert = pollingDao.save(polling);
			commit();

			InsertPollingDtoDataRes dataDto = new InsertPollingDtoDataRes();
			dataDto.setId(pollingInsert.getId());
			
			if(pollingInsert != null) {
				for (int i = 0; i < data.getChoiceName().size(); i++) {
					InsertPollingChoiceDtoReq choiceReq = new InsertPollingChoiceDtoReq();
					choiceReq.setChoiceName(data.getChoiceName().get(i));
					choiceReq.setPollingId(pollingInsert.getId());
					
					pollingChoiceService.insert(choiceReq);
				}
			}

			insert.setData(dataDto);
			insert.setMsg(null);
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public DeleteByPollingIdDtoRes deleteById(String id) throws Exception {
		DeleteByPollingIdDtoRes deleteById = new DeleteByPollingIdDtoRes();

		try {
			begin();
			boolean isDeleted = pollingDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetPollingByThreadIdDtoRes findByThread(String id) throws Exception {
		GetPollingByThreadIdDtoRes getByThread = new GetPollingByThreadIdDtoRes();

		Polling polling = pollingDao.findByThread(id);
		GetPollingByThreadIdDtoDataRes data = new GetPollingByThreadIdDtoDataRes();

		data.setId(polling.getId());
		data.setPollingName(polling.getPollingName());
		data.setThreadId(polling.getThreadId().getId());
		data.setThreadTitle(polling.getThreadId().getThreadTitle());
		data.setIsPremium(polling.getThreadId().getIsPremium());
		data.setCreatedBy(polling.getCreatedBy());
		data.setCreatedAt(polling.getCreatedAt());
		data.setVersion(polling.getVersion());
		data.setIsActive(polling.getIsActive());

		getByThread.setData(data);
		getByThread.setMsg(null);

		return getByThread;
	}
}
