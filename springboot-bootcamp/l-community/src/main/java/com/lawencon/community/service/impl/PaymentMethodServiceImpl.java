package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.PaymentMethodDao;
import com.lawencon.community.dto.paymentmethod.DeleteByPaymentMethodIdDtoRes;
import com.lawencon.community.dto.paymentmethod.GetAllPaymentMethodDtoDataRes;
import com.lawencon.community.dto.paymentmethod.GetAllPaymentMethodDtoRes;
import com.lawencon.community.dto.paymentmethod.GetByPaymentMethodIdDtoDataRes;
import com.lawencon.community.dto.paymentmethod.GetByPaymentMethodIdDtoRes;
import com.lawencon.community.dto.paymentmethod.InsertPaymentMethodDtoDataRes;
import com.lawencon.community.dto.paymentmethod.InsertPaymentMethodDtoReq;
import com.lawencon.community.dto.paymentmethod.InsertPaymentMethodDtoRes;
import com.lawencon.community.dto.paymentmethod.UpdatePaymentMethodDtoDataRes;
import com.lawencon.community.dto.paymentmethod.UpdatePaymentMethodDtoReq;
import com.lawencon.community.dto.paymentmethod.UpdatePaymentMethodDtoRes;
import com.lawencon.community.model.PaymentMethod;
import com.lawencon.community.service.PaymentMethodService;
import com.lawencon.model.SearchQuery;

@Service
public class PaymentMethodServiceImpl extends BaseService implements PaymentMethodService {
	private PaymentMethodDao paymentMethodDao;

	@Autowired
	public PaymentMethodServiceImpl(PaymentMethodDao paymentMethodDao) {
		this.paymentMethodDao = paymentMethodDao;
	}
	
	@Override
	public GetAllPaymentMethodDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllPaymentMethodDtoRes getAll = new GetAllPaymentMethodDtoRes();
		
		SearchQuery<PaymentMethod> paymentMethods = paymentMethodDao.findAll(query, startPage, maxPage);
		List<GetAllPaymentMethodDtoDataRes> listPaymentMethod = new ArrayList<>();

		for (int i = 0; i < paymentMethods.getData().size(); i++) {
			PaymentMethod paymentMethod = paymentMethods.getData().get(i);
			GetAllPaymentMethodDtoDataRes data = new GetAllPaymentMethodDtoDataRes();

			data.setId(paymentMethod.getId());
			data.setPaymentCode(paymentMethod.getPaymentCode());
			data.setPaymentName(paymentMethod.getPaymentName());
			data.setVersion(paymentMethod.getVersion());
			data.setIsActive(paymentMethod.getIsActive());

			listPaymentMethod.add(data);
		}

		getAll.setData(listPaymentMethod);
		getAll.setMsg(null);
		getAll.setTotal(paymentMethods.getCount());

		return getAll;
	}
	
	@Override
	public GetByPaymentMethodIdDtoRes findById(String id) throws Exception {
		GetByPaymentMethodIdDtoRes getById = new GetByPaymentMethodIdDtoRes();

		PaymentMethod paymentMethod = paymentMethodDao.findById(id);
		GetByPaymentMethodIdDtoDataRes data = new GetByPaymentMethodIdDtoDataRes();

		data.setId(paymentMethod.getId());
		data.setPaymentCode(paymentMethod.getPaymentCode());
		data.setPaymentName(paymentMethod.getPaymentName());
		data.setVersion(paymentMethod.getVersion());
		data.setIsActive(paymentMethod.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertPaymentMethodDtoRes insert(InsertPaymentMethodDtoReq data) throws Exception {
		InsertPaymentMethodDtoRes insert = new InsertPaymentMethodDtoRes();

		try {
			validateInsert(data);
			
			PaymentMethod paymentMethod = new PaymentMethod();
			paymentMethod.setPaymentCode(getAlphaNumericString(5));
			paymentMethod.setPaymentName(data.getPaymentName());
			paymentMethod.setCreatedBy(getId());
			
			begin();
			PaymentMethod paymentMethodInsert = paymentMethodDao.save(paymentMethod);
			commit();
			
			InsertPaymentMethodDtoDataRes dataDto = new InsertPaymentMethodDtoDataRes();
			dataDto.setId(paymentMethodInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(CommonConstant.ACTION_ADD.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Payment Method " + CommonConstant.HAS_BEEN_ADDED.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdatePaymentMethodDtoRes update(UpdatePaymentMethodDtoReq data) throws Exception {
		UpdatePaymentMethodDtoRes update = new UpdatePaymentMethodDtoRes();

		try {
			validateUpdate(data);
			
			if (data.getVersion() != null) {
				PaymentMethod paymentMethod = paymentMethodDao.findById(data.getId());
				paymentMethod.setPaymentName(data.getPaymentName());
				paymentMethod.setUpdatedBy(getId());
				paymentMethod.setVersion(data.getVersion());

				if (data.getIsActive() != null) {
					paymentMethod.setIsActive(data.getIsActive());
				}

				begin();
				PaymentMethod userUpdate = paymentMethodDao.save(paymentMethod);
				commit();

				UpdatePaymentMethodDtoDataRes dataDto = new UpdatePaymentMethodDtoDataRes();
				dataDto.setVersion(userUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Payment Method " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByPaymentMethodIdDtoRes deleteById(String id) throws Exception {
		DeleteByPaymentMethodIdDtoRes deleteById = new DeleteByPaymentMethodIdDtoRes();

		try {
			validateDelete(id);
			
			begin();
			boolean isDeleted = paymentMethodDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Payment Method " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	private void validateInsert(InsertPaymentMethodDtoReq data) throws Exception {
		if (data.getPaymentCode() == null || data.getPaymentCode().trim().equals("")) {
			throw new Exception("Payment Code Can't Be Null");
		} else {
			PaymentMethod paymentMethod = paymentMethodDao.findByCode(data.getPaymentCode());
			if (paymentMethod != null) {
				throw new Exception("Payment Code Already Existed");
			}
			if (data.getPaymentName() == null || data.getPaymentName().trim().equals("")) {
				throw new Exception("Payment Name Can't Be Null");
			}
		}
	}
	
	private void validateUpdate(UpdatePaymentMethodDtoReq data) throws Exception {
		if (data.getId() == null || data.getId().trim().equals("")) {
			throw new Exception("Payment Id Can't Be Null");
		} else {
			PaymentMethod paymentMethod = paymentMethodDao.findById(data.getId());
			if (data.getPaymentName() == null || data.getPaymentName().trim().equals("")) {
				throw new Exception("Payment Name Can't Be Null");
			}
			if (paymentMethod.getVersion() != data.getVersion()) {
				throw new Exception("Payment That You Update Already Updated By Someone");
			}
		}
	}

	private void validateDelete(String id) throws Exception {
		PaymentMethod paymentMethod = paymentMethodDao.findById(id);
		
		if(paymentMethod == null) {
			throw new Exception("Payment Id Is Not Exist");
		}
	}
	
}
