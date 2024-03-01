package com.interview.geoweather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherForecastResponse {

    @JsonProperty("full_name")
    private String dayName;

    @JsonProperty("temp_high_celcus")
    private double tempHighCelsius;

    @JsonProperty("forecast_blurp")
    private String forecastBlurp;
}
