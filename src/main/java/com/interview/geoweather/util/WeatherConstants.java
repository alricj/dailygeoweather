package com.interview.geoweather.util;

import java.time.format.DateTimeFormatter;

public class WeatherConstants {
    public final static String URI_HOST = "https://api.weather.gov";
    public final static String ENDPOINT_URl = "/gridpoints/MLB/33,70/forecast";
    public  static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
}
