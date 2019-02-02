package com.quartzjob.detailmode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.quartzjob.service.EmpManager;

public class DetailModeTestJob extends QuartzJobBean {

	public static final Log log = LogFactory.getLog(DetailModeTestJob.class);

	// 判断作业是否执行的旗标
	private boolean isRunning = false;

	// 该作业类所依赖的业务逻辑组件
	private EmpManager empMgr;

	public void setEmpMgr(EmpManager empMgr) {
		this.empMgr = empMgr;
	}

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		if (!isRunning) {
			System.out.println("execute detail task 1-1");
			isRunning = true;
			// 调用业务逻辑方法
			try {
				empMgr.autoWrite();
				empMgr.opclient();
			} catch (Exception e) {
				log.info("--error detail tast 1-1-->", e);
				e.printStackTrace();
			}
			log.info("execute detail task 1-1");
			
			isRunning = false;
		}
	}

}
