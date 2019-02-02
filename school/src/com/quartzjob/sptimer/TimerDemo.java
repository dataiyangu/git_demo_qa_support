package com.quartzjob.sptimer;

import java.util.Date;
import java.util.TimerTask;

public class TimerDemo extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("-----timerDemo-->" + new Date(System.currentTimeMillis()));
	}

}
