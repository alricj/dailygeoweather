package com.interview.geoweather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class WeatherData {

    @JsonProperty("@context")
    private List<Object> context;

    @JsonIgnore
    private String type;

    List<ForecastPeriod> periods;

    public List<ForecastPeriod> getPeriods() {
        return periods;
    }

    public void setPeriods(List<ForecastPeriod> periods) {
        this.periods = periods;
    }

    public static void main(String[] args) {

        try{
            dojson();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }


    }

    private static void dojson() throws IOException {

        File file = new File("/Users/alric/myapps/geoweather/src/main/resources/weather.json");
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherForecast weatherForecast = objectMapper.readValue(file, WeatherForecast.class);

        System.out.println(weatherForecast.getProperties().getPeriods().get(0).getName());
        System.out.println(weatherForecast.getProperties().getPeriods().get(0).getTemperature());
        System.out.println(weatherForecast.getProperties().getPeriods().get(0).getShortForecast());


    }

}




