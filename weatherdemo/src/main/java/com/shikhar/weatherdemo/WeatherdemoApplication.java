/**
 * 
 */
package com.shikhar.weatherdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *	@author Shikhar 
 */
@SpringBootApplication
@EnableScheduling
public class WeatherdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherdemoApplication.class, args);
	}

}
