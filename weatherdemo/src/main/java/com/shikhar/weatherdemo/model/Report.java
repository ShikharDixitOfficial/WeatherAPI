/**
 * 
 */
package com.shikhar.weatherdemo.model;

/**
 * @author Shikhar
 *
 */
public class Report {
	
	public String min;
	
	public String max;
	
	public String date;
	
	public String city;

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Report [min=" + min + ", max=" + max + ", date=" + date + ", city=" + city + "]";
	}
	
}
