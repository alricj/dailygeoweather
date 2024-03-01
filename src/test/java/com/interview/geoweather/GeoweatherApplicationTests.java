package com.interview.geoweather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.geoweather.model.WeatherForecast;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class GeoweatherApplicationTests {
	Logger LOGGER = LoggerFactory.getLogger(GeoweatherApplicationTests.class);
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldReturnDailyWeather() throws Exception {
		Logger LOGGER = LoggerFactory.getLogger(GeoweatherApplicationTests.class);

		MvcResult result  = mockMvc.perform(MockMvcRequestBuilders
						.get("http://localhost:8088/geoweather/v1/forecast")
						.contentType(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk())
				.andReturn();

		Assertions.assertNotNull(result.getResponse());

	}

	@Test
	void shouldDisplayFirstDailyRecord() throws IOException {
		ClassPathResource resource      = new ClassPathResource("weather.json");
		ObjectMapper objectMapper       = new ObjectMapper();
		WeatherForecast weatherForecast = objectMapper.readValue(resource.getInputStream(), WeatherForecast.class);

		Mono<?> weathermono = Mono.just(weatherForecast).log();
		weathermono.subscribe(p-> {
			LOGGER.info(String.format("**day_name: %s", weatherForecast.getProperties().getPeriods().get(0).getName()));
			LOGGER.info(String.format("**temp_high_celsius:  %s", weatherForecast.getProperties().getPeriods().get(0).getTemperature()));
			LOGGER.info(String.format("**forecast_blurp: %s", weatherForecast.getProperties().getPeriods().get(0).getShortForecast()));
		});
		Assertions.assertNotNull(weatherForecast.getProperties().getPeriods());
	}

}
