/**
 * 
 */
package com.shikhar.weatherdemo.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shikhar.weatherdemo.service.Service;

/**
 * @author Shikhar
 *
 */
@RestController
public class Controller {
	
	private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
	
	@Autowired
	Service service;
	
	/**
	 * 
	 * @param date
	 * @return weather detail for the given date
	 * 
	 * Note: 'date' can only be for the last 7 days(due to weatherapi.com key limitations) in the format of yyyy-MM-dd 
	 * 
	 */
	
	@GetMapping(path = "/HistoricData/{city}/v1/{date}")
	public String getHistoricData(@PathVariable String city,@PathVariable String date) {
		LOGGER.info("Calling Historic Weather Data for " + city + " from :: " + date);
		return service.callHistoricWeather(city, date);
	}
}
