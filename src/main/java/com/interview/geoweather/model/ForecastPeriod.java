package com.interview.geoweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastPeriod {
    private String name;
    private String startTime;
    private int temperature;
    private String shortForecast;

}
