package com.quartzjob.task.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TradeService {
	
	private static final Log log = LogFactory.getLog(TradeService.class);
	
	public void getAll(String taskName, String taskType, int taskId){
		
		log.info("getAll--tn-"+taskName + "-type-"+taskType + "-tid-"+taskId);
	}
	
	public void saveInc(String taskName, String taskType, int taskId){
//		int ee = 1/0;
		log.info("saveInc--tn-"+taskName + "-type-"+taskType + "-tid-"+taskId);
	}
	
	public void getInc(String taskName, String taskType, int taskId){
		
		log.info("getInc--tn-"+taskName + "-type-"+taskType + "-tid-"+taskId);
	}
	
	public void saveAll(String taskName, String taskType, int taskId){
		
		log.info("saveAll--tn-"+taskName + "-type-"+taskType + "-tid-"+taskId);
	}
	
}
