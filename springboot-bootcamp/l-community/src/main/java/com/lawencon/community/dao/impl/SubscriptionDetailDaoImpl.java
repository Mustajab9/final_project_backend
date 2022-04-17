package com.lawencon.community.dao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.SubscriptionDetailDao;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.PriceListMember;
import com.lawencon.community.model.Profiles;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.Subscription;
import com.lawencon.community.model.SubscriptionDetail;
import com.lawencon.community.model.User;

@Repository
public class SubscriptionDetailDaoImpl extends BaseDao<SubscriptionDetail> implements SubscriptionDetailDao {
	
	@Override
	public List<SubscriptionDetail> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public SubscriptionDetail findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public SubscriptionDetail save(SubscriptionDetail entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<SubscriptionDetail> findBySubscription(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT sd.id, plm.id, plm.price_code, plm.price_nominal, plm.duration,"); 
		builder.append(" s.id, s.subscription_code, s.subscription_duration, s.is_approve,"); 
		builder.append(" p.profile_code, p.profile_name, p.profile_company, a.attachment_content,");
		builder.append(" u.email, r.role_name, r.role_code, sd.version, sd.is_active");
		builder.append(" FROM subscription_detail sd");
		builder.append(" INNER JOIN price_list_member plm ON plm.id = sd.price_id");
		builder.append(" INNER JOIN subscriptions s ON s.id = sd.subscription_id");
		builder.append(" INNER JOIN profiles p ON p.id = s.profile_id");
		builder.append(" LEFT JOIN attachments a ON a.id = p.profile_image");
		builder.append(" INNER JOIN users u ON u.id = p.user_id");
		builder.append(" INNER JOIN roles r ON r.id = u.role_id");
		builder.append(" WHERE s.id = :id");
		
		List<?> results = createNativeQuery(builder.toString()).setParameter("id", id).getResultList();
		List<SubscriptionDetail> listResult = new ArrayList<>();
		
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			SubscriptionDetail subsDetail = new SubscriptionDetail();
			subsDetail.setId(obj[0].toString());
			
			PriceListMember priceListMember = new PriceListMember();
			priceListMember.setId(obj[1].toString());
			priceListMember.setPriceCode(obj[2].toString());
			priceListMember.setPriceNominal(BigInteger.valueOf(((Number) obj[3]).longValue()));
			priceListMember.setDuration(Integer.valueOf(obj[4].toString()));
			subsDetail.setPriceId(priceListMember);
			
			Subscription subscription = new Subscription();
			subscription.setId(obj[5].toString());
			subscription.setSubscriptionCode(obj[6].toString());
			subscription.setSubscriptionDuration((Date)obj[7]);
			subscription.setIsApprove(Boolean.valueOf(obj[8].toString()));
			
			Profiles profile = new Profiles();
			profile.setProfileCode(obj[9].toString());
			profile.setProfileName(obj[10].toString());
			profile.setProfileCompany(obj[11].toString());
			
			if(obj[12]!=null) {
				Attachment attach = new Attachment();
                byte[] content = null;
                try {
                    content = convertObjToByteArray(obj[12].toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                attach.setAttachmentContent(content);
                profile.setProfileImage(attach);
			}
			
			User user = new User();
			user.setEmail(obj[13].toString());
			
			Role role = new Role();
			role.setRoleName(obj[14].toString());
			role.setRoleCode(obj[15].toString());
			user.setRoleId(role);
			profile.setUserId(user);
			
			subscription.setProfileId(profile);
			subsDetail.setSubscriptionId(subscription);
			
			subsDetail.setVersion(Integer.valueOf(obj[16].toString()));
			subsDetail.setIsActive(Boolean.valueOf(obj[17].toString()));
			
			listResult.add(subsDetail);
		});
		
		return listResult;
	}
	
	@Override
	public List<?> validateDelete(String id) throws Exception {
		String sql = "SELECT s.id FORM subscriptions AS s WHERE s.id = ?1";
		
		List<?> listObj = createNativeQuery(sql).setParameter(1, id).setMaxResults(1).getResultList();
		List<String> result = new ArrayList<>();
		
		listObj.forEach(val -> {
			Object obj = (Object) val;
			result.add(obj != null ? obj.toString() : null);
		});
		
		return result;
	}
}
