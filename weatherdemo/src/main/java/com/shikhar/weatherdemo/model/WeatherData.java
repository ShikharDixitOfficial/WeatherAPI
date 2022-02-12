/**
 * 
 */
package com.shikhar.weatherdemo.model;

/**
 * @author Shikhar
 *
 */
public class WeatherData {
	
	public String date;
	
	public String time;
	
	public String data;
	
	public String city;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "WeatherData [date=" + date + ", time=" + time + ", data=" + data + ", city=" + city + "]";
	}
	
	

}
