package com.ilucky.quartz;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            JobDetail job = new JobDetail();
            job.setName("dummyJobName");
            //job.setJobClass(HelloJobImpl.class);
            job.setJobClass(HelloJob1.class);

            //configure the scheduler time
            SimpleTrigger trigger = new SimpleTrigger();
            trigger.setName("dumyTriggerName");
            trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
            trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
            trigger.setRepeatInterval(10000);

            //schedule it
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
