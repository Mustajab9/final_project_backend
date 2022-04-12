package com.lawencon.community.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ProfilesDao;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Industry;
import com.lawencon.community.model.Position;
import com.lawencon.community.model.Profiles;
import com.lawencon.community.model.Province;
import com.lawencon.community.model.Regency;
import com.lawencon.community.model.User;

@Repository
public class ProfilesDaoImpl extends BaseDao<Profiles> implements ProfilesDao {
	
	@Override
	public List<Profiles> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public Profiles findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Profiles save(Profiles entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public Profiles findByUser(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT u.id AS user_id, u.email, p.id, p.profile_name, p.profile_company, p.profile_phone, a.id AS attachment_id, a.attachment_extension, i.id AS industry_id, i.industry_name,");
		builder.append(" po.id AS position_id, po.position_name, r.id AS regency_id, pr.id AS province_id, pr.province_name, p.version, p.is_active");
		builder.append(" FROM profiles AS p");
		builder.append(" LEFT JOIN attachments AS a ON a.id = p.profile_image");
		builder.append(" INNER JOIN industries AS i ON i.id = p.industry_id");
		builder.append(" INNER JOIN positions AS po ON po.id = p.position_id");
		builder.append(" LEFT JOIN regencies AS r ON r.id = p.regency_id");
		builder.append(" LEFT JOIN provinces AS pr ON pr.id = r.province_id");
		builder.append(" INNER JOIN users AS u ON u.id = p.user_id ");
		builder.append(" WHERE u.id = :id");
		
		Profiles profile = new Profiles();
		try {
			Object result = createNativeQuery(builder.toString())
					.setParameter("id", id)
					.getSingleResult();
			
			Object[] obj = (Object[]) result;
			
			User user = new User();
			user.setId(obj[0].toString());
			user.setEmail(obj[1].toString());
			profile.setUserId(user);
			
			profile.setId(obj[2].toString());
			profile.setProfileName(obj[3].toString());
			profile.setProfileCompany(obj[4].toString());
			profile.setProfilePhone(obj[5].toString());
			
			if(obj[6] != null) {
				Attachment attachment = new Attachment();
				attachment.setId(obj[6].toString());
				attachment.setAttachmentExtension(obj[7].toString());
				profile.setProfileImage(attachment);
			}
			
			Industry industry = new Industry();
			industry.setId(obj[8].toString());
			industry.setIndustryName(obj[9].toString());
			profile.setIndustryId(industry);
			
			Position position = new Position();
			position.setId(obj[10].toString());
			position.setPositionName(obj[11].toString());
			profile.setPositionId(position);
			
			if(obj[12] != null) {
				Regency regency = new Regency();
				regency.setId(obj[12].toString());
				
				Province province = new Province();
				province.setId(obj[13].toString());
				province.setProvinceName(obj[14].toString());
				regency.setProvinceId(province);
				
				profile.setRegencyId(regency);
			}
			
			profile.setVersion(Integer.valueOf(obj[15].toString()));
			profile.setIsActive(Boolean.valueOf(obj[16].toString()));
		}catch (NoResultException e) {}

		return profile;
	}
	
}
