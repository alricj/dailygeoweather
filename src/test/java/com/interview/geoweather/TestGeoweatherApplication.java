package com.interview.geoweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestGeoweatherApplication {

	public static void main(String[] args) {
		SpringApplication.from(GeoweatherApplication::main).with(TestGeoweatherApplication.class).run(args);
	}

}
