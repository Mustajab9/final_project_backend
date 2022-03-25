package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.dao.ProfilesDao;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Industry;
import com.lawencon.community.model.Position;
import com.lawencon.community.model.Profiles;
import com.lawencon.community.model.Province;

@Repository
public class ProfilesDaoImpl extends BaseDaoImpl<Profiles> implements ProfilesDao {
	
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
		builder.append("SELECT p.profile_name, p.profile_company, a.id, a.attachment_extension, i.id, i.industry_name,");
		builder.append(" po.id, po.position_name, pr.id, pr.province_name p.version, p.is_active");
		builder.append(" FROM profiles AS p");
		builder.append(" LEFT JOIN attachment AS a ON a.id = p.profile_image");
		builder.append(" INNER JOIN industries AS i ON i.id = p.industry_id");
		builder.append(" INNER JOIN positions AS po ON po.id = p.position_id");
		builder.append(" INNER JOIN provinces AS pr ON pr.id = p.province_id");
		builder.append(" INNER JOIN users AS u ON u.id = p.user_id ");
		builder.append(" WHERE u.id = :id");
		
		Object result = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getSingleResult();
		
		Object[] obj = (Object[]) result;
		
		
		Profiles profile = new Profiles();
		profile.setProfileName(obj[0].toString());
		profile.setProfileCompany(obj[1].toString());
		
		if(obj[2] != null) {
			Attachment attachment = new Attachment();
			attachment.setId(obj[2].toString());
			attachment.setAttachmentExtension(obj[3].toString());
			profile.setProfileImage(attachment);
		}
		
		Industry industry = new Industry();
		industry.setId(obj[4].toString());
		industry.setIndustryName(obj[5].toString());
		profile.setIndustryId(industry);
		
		Position position = new Position();
		position.setId(obj[6].toString());
		position.setPositionName(obj[7].toString());
		profile.setPositionId(position);
		
		Province province = new Province();
		province.setId(obj[8].toString());
		province.setProvinceName(obj[9].toString());
		profile.setProvinceId(province);
		
		profile.setVersion(Integer.valueOf(obj[10].toString()));
		profile.setIsActive(Boolean.valueOf(obj[11].toString()));

		return profile;
	}
	
}
