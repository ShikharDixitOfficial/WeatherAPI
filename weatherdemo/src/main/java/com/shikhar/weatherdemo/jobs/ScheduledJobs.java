/**
 * 
 */
package com.shikhar.weatherdemo.jobs;


import java.util.Calendar;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.shikhar.weatherdemo.service.Service;

/**
 * @author Shikhar
 *
 */
@Component("scheduledjobs")
public class ScheduledJobs {
	
	private static final Logger LOGGER = Logger.getLogger(ScheduledJobs.class.getName());
	
	@Autowired
	Service service;
	
	
	/**
	 * Scheduled Hourly Job
	 */
	@Scheduled(cron = "0 0 * * * *", zone = "Asia/Kolkata")
	public void hourlyJob() {
		LOGGER.info("Current time is :: " + Calendar.getInstance().getTime());
		service.callCurrentWeather();
	}
	
	
	/**
	 * Scheduled Daily Job
	 */
	@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Kolkata")
	public void dailyJob() {
		LOGGER.info("Current time is :: " + Calendar.getInstance().getTime());
		service.generateReport();
	}

}
