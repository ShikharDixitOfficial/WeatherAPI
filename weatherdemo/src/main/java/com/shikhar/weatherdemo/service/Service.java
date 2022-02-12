/**
 * 
 */
package com.shikhar.weatherdemo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.shikhar.weatherdemo.dao.Dao;
import com.shikhar.weatherdemo.model.Report;
import com.shikhar.weatherdemo.model.WeatherData;

/**
 * @author Shikhar
 *
 */

@Component
public class Service {
	
	private static final Logger LOGGER = Logger.getLogger(Service.class.getName());
	private static final String DELHI = "Delhi";
	private static final String MUMBAI = "Mumbai";
	private static final String KOLKATA = "Kolkata";
	private static final String CHENNAI = "Chennai";
	private static final String KEY = "d7ac9d0c72b7474e8ab11942221202";
	
	@Autowired
	Dao dao;
	
	public String callHistoricWeather(String city,String date) {
		LOGGER.info("Calling WeatherAPI.com Data for " + city + " from :: " + date);
		String uri = "http://api.weatherapi.com/v1/history.json?key=" + KEY + "&q=" + city + "&dt=" + date;
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(uri, String.class);
	}
	
	public void callCurrentWeather() {
		LOGGER.info("Calling and Storing WeatherAPI.com Current Weather Data for " + DELHI);
		dao.storeDataInDB(generateWeatherData(DELHI));
		
		LOGGER.info("Calling and Storing WeatherAPI.com Current Weather Data for " + MUMBAI);
		dao.storeDataInDB(generateWeatherData(MUMBAI));
		
		LOGGER.info("Calling and Storing WeatherAPI.com Current Weather Data for " + KOLKATA);
		dao.storeDataInDB(generateWeatherData(KOLKATA));
		
		LOGGER.info("Calling and Storing WeatherAPI.com Current Weather Data for " + CHENNAI);
		dao.storeDataInDB(generateWeatherData(CHENNAI));
	}
	
	public WeatherData generateWeatherData(String city) {
		WeatherData weatherData = new WeatherData();
		weatherData.setCity(city);
		
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://api.weatherapi.com/v1/current.json?key=d7ac9d0c72b7474e8ab11942221202&aqi=no&q=";
		weatherData.setData(restTemplate.getForObject(uri+city, String.class));
		
		weatherData.setDate(currentDate());
		
		weatherData.setTime(currentTime());
		
		return weatherData;
	}
	
	private String currentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(); 
        return dateFormat.format(date);
	}
	
	private String currentTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public void generateReport() {
		
		//For Delhi
		dao.storeReportInDB(generateReportForCity(DELHI));
		
		//For Mumbai
		dao.storeReportInDB(generateReportForCity(MUMBAI));
		
		//For Kolkota
		dao.storeReportInDB(generateReportForCity(KOLKATA));
		
		//For Chennai
		dao.storeReportInDB(generateReportForCity(CHENNAI));
	}
	
	private Report generateReportForCity(String city) {
		Report report = new Report();
		report.setDate(yesterdaysDate());
		report.setMin(calcMinTemp(city));
		report.setMax(calcMaxTemp(city));
		report.setCity(city);
		return report;
	}
	
	private String yesterdaysDate() {
		LocalDate date = LocalDate.now();
		return date.minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	private String calcMinTemp(String city) { 
		List<String> list = dao.yesterdaysTempDataFromDB(city,yesterdaysDate());
		float fMin = Float.MAX_VALUE;
		for(String s : list) {
			float f = Float.parseFloat(s.substring(s.indexOf("temp_c")+8,s.indexOf("temp_c")+12));
			if(fMin>f) {
				fMin=f;
			} 
		}
		return ""+fMin;
	}
	
	private String calcMaxTemp(String city) {
		List<String> list = dao.yesterdaysTempDataFromDB(city,yesterdaysDate());
		float fMax = Float.MIN_VALUE;
		for(String s : list) {
			float f = Float.parseFloat(s.substring(s.indexOf("temp_c")+8,s.indexOf("temp_c")+12));
			if(fMax<f) {
				fMax=f;
			}
		}
		return ""+fMax;
	}

}
