package com.lawencon.community.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.service.SubscriptionService;

@Component
public class SubscriptionJob implements Job {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			subscriptionService.expiredSubscription();
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			ConnHandler.commit();
			ConnHandler.clear();			
		}
		
	}
}
