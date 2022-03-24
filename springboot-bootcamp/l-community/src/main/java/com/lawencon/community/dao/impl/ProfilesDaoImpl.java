package com.lawencon.community.dao.impl;

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
	public Profiles getByUser(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT p.profile_name, p.profile_company, p.profile_image, i.id, i.industry_name, ");
		builder.append("po.id, po.position_name, pr.id, pr.province_name p.\"version\", p.is_active ");
		builder.append("FROM profiles AS p ");
		builder.append("LEFT JOIN attachment AS a ON a.id = p.profile_image ");
		builder.append("INNER JOIN industries AS i ON i.id = p.industry_id ");
		builder.append("INNER JOIN positions AS po ON po.id = p.position_id ");
		builder.append("INNER JOIN provinces AS pr ON pr.id = p.province_id ");
		builder.append("INNER JOIN users AS u ON u.id = p.user_id ");
		builder.append("WHERE u.id = :id");
		
		Object result = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getSingleResult();
		
		Object[] obj = (Object[]) result;
		
		
		Profiles profile = new Profiles();
		profile.setProfileName(obj[0].toString());
		profile.setProfileCompany(obj[1].toString());
		
		Attachment attachment = new Attachment();
		attachment.setId(obj[2].toString());
		profile.setProfileImage(attachment);
		
		Industry industry = new Industry();
		industry.setId(obj[3].toString());
		industry.setIndustryName(obj[4].toString());
		profile.setIndustryId(industry);
		
		Position position = new Position();
		position.setId(obj[5].toString());
		position.setPositionName(obj[6].toString());
		profile.setPositionId(position);
		
		Province province = new Province();
		province.setId(obj[7].toString());
		province.setProvinceName(obj[8].toString());
		profile.setProvinceId(province);
		
		profile.setVersion(Integer.valueOf(obj[9].toString()));
		profile.setIsActive(Boolean.valueOf(obj[10].toString()));

		return profile;
	}

}
