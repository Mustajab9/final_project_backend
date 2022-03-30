package com.lawencon.community.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.SubscriptionDao;
import com.lawencon.community.model.Profiles;
import com.lawencon.community.model.Subscription;

@Repository
public class SubscriptionDaoImpl extends BaseDao<Subscription> implements SubscriptionDao {
	
	@Override
	public List<Subscription> findAll(int startPage, int maxPage) throws Exception {
		return super.getAll(startPage, maxPage);
	}
	
	@Override
	public Subscription findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Subscription save(Subscription entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public boolean update(Date date, Integer lenghtDay, String id, String userId) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE subscriptions");
		builder.append(" SET subscription_duration = DATE(:date) + (:lenghtDay || ' day')\\:\\:interval,");
		builder.append(" version = version + 1, updated_by = :userId, updated_at = NOW()");
		builder.append(" WHERE id = :id");
		
		Integer update = createNativeQuery(builder.toString())
											.setParameter("date", sdf.format(date))
											.setParameter("lenghtDay", lenghtDay)
											.setParameter("userId", userId)
											.setParameter("id", id)
											.executeUpdate();
		boolean isSuccessUpdate = false;
		if(update > 0) {
			isSuccessUpdate = true;
		}
			
		return isSuccessUpdate;
	}
	
	@Override
	public Subscription findByUser(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT s.id, s.subscription_code, s.subscription_duration, s.is_approve, p.id, p.profile_name, s.version. s.is_active");
		builder.append(" FROM subscriptions s");
		builder.append(" JOIN profiles p ON p.id = s.profile_id");
		builder.append(" WHERE p.user_id = :id");
		
		Subscription data = null;
		try {
			Object result = createNativeQuery(builder.toString())
								.setParameter("id", id)
								.getSingleResult();
			
			Object[] obj = (Object[]) result;
			data = new Subscription();
			
			data.setId(obj[0].toString());
			data.setSubscriptionCode(obj[1].toString());
			data.setSubscriptionDuration((java.sql.Date)obj[2]);
			data.setIsApprove(Boolean.valueOf(obj[3].toString()));
			
			Profiles profiles = new Profiles();
			profiles.setId(obj[4].toString());
			profiles.setProfileName(obj[5].toString());
			data.setProfileId(profiles);
			
			data.setVersion(Integer.valueOf(obj[6].toString()));
			data.setIsActive(Boolean.valueOf(obj[7].toString()));
			
		}catch(NoResultException | NonUniqueResultException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
