package com.lawencon.community.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ProfileSosmedDao;
import com.lawencon.community.model.ProfileSosmed;
import com.lawencon.community.model.Profiles;
import com.lawencon.community.model.SocialMedia;

@Repository
public class ProfileSosmedDaoImpl extends BaseDao<ProfileSosmed> implements ProfileSosmedDao {
	
	@Override
	public List<ProfileSosmed> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public ProfileSosmed findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public ProfileSosmed save(ProfileSosmed entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<ProfileSosmed> findByUser(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ps.id, p.id, p.profile_name, sm.id, sm.social_media_name, ps.version, ps.is_active");
		builder.append("FROM profile_sosmed ps");
		builder.append("JOIN profiles p ON p.id = ps.profile_id");
		builder.append("JOIN social_media sm ON sm.id = ps.social_media_id");
		builder.append("WHERE p.user_id = :id");
		
		List<?> results = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getResultList();
		List<ProfileSosmed> listResult = new ArrayList<>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			ProfileSosmed data = new ProfileSosmed();
			
			data.setId(obj[0].toString());
			
			Profiles profiles = new Profiles();
			profiles.setId(obj[1].toString());
			profiles.setProfileName(obj[2].toString());
			data.setProfileId(profiles);
			
			SocialMedia socialMedia = new SocialMedia();
			socialMedia.setId(obj[3].toString());
			socialMedia.setSocialMediaName(obj[4].toString());
			data.setSocialMediaId(socialMedia);
			
			data.setVersion(Integer.valueOf(obj[5].toString()));
			data.setIsActive(Boolean.valueOf(obj[6].toString()));
			
			listResult.add(data);
		});
		
		return listResult;
	}
}
