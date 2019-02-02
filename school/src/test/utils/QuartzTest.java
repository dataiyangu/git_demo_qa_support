package test.utils;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;

public class QuartzTest implements Job {

    static Logger logger = Logger.getLogger(QuartzTest.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Generating UserName - "
                + context.getJobDetail().getJobDataMap().get("UserName"));
    }

    public static void main(String[] args) {
        try {
            SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            sched.start();
            JobDetail jobDetail = new JobDetail("QuartzTestJob",
                    "QuartzTestJobGroup", QuartzTest.class);
            //JobDetail
            System.out.println(jobDetail.getName());
            System.out.println(jobDetail.getGroup());
            System.out.println(jobDetail.getFullName());
            
            jobDetail.getJobDataMap().put("UserName", "Miao Yachun");
            CronTrigger trigger = new CronTrigger("QuartzTestJob",
                    "QuartzTestJobGroup");
            //Trigger
            System.out.println(trigger.getName());
            System.out.println(trigger.getGroup());
            System.out.println(trigger.getFullName());
            trigger.setStartTime(new Date());
          //每月11号中午23:00:00时刻执行作业
            trigger.setCronExpression("00 00 23 11 * ? *");
            //每10秒执行作业
            trigger.setCronExpression("0/10 * * * * ? *");
            sched.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
