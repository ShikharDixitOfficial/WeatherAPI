/**
 * 
 */
package com.shikhar.weatherdemo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.shikhar.weatherdemo.model.Report;
import com.shikhar.weatherdemo.model.WeatherData;

/**
 * @author Shikhar
 *
 */

@Component
public class Dao {

	private static final Logger LOGGER = Logger.getLogger(Dao.class.getName());
	
	@Autowired
	JdbcTemplate jdbc;
	
	public void storeDataInDB(WeatherData weatherData) {
		jdbc.execute("insert into weatherdata(date,time,data,city) value (\'" + weatherData.date + "\',\'" + weatherData.time + "\',\'" + weatherData.data + "\',\'" + weatherData.city + "\')");
		LOGGER.info("Data Inserted into table weatherdata successfully.");
	}
	
	public void storeReportInDB(Report report) {
		jdbc.execute("insert into report(max,min,date,city) value (\'" + report.max + "\',\'" + report.min + "\',\'" + report.date + "\',\'" + report.city + "\')");
		LOGGER.info("Data Inserted into table report successfully.");
	}
	
	public List<String> yesterdaysTempDataFromDB(String city, String date) {
		LOGGER.info("Data call to table weatherdata successfull.");
		return jdbc.query("select data from weatherdata where city = \'" + city + "\' and date =\'" + date + "\'", new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<String> data = new ArrayList<String>();
				while(rs.next()) {
					data.add(rs.getString(1));
				}
				return data;
			}
			
		});
		
	}

}
