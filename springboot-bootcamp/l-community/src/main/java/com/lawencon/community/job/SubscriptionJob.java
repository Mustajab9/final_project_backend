package com.lawencon.community.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionJob implements Job {
	
//	private 
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
//		System.out.println("Masuk nih");
		
	}
}
