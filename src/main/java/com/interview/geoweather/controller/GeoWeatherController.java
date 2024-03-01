package com.interview.geoweather.controller;

import com.interview.geoweather.model.WeatherForecastResponse;
import com.interview.geoweather.service.GeoWeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.EntityResponse;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/geoweather/v1")
public class GeoWeatherController {

    private GeoWeatherService geoWeatherService;

    public GeoWeatherController(GeoWeatherService geoWeatherService) {
        this.geoWeatherService = geoWeatherService;
    }

//    @GetMapping("/forecast")
//    public Mono<WeatherForecastResponse> _getTodaysWeatherForecast(){
//        Mono<WeatherForecastResponse> monoresponse = this.geoWeatherService.getTodaysDayForecast();
//
//
//        return monoresponse;
//
//    }

    @GetMapping("/forecast")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<?>> getTodaysWeatherForecast(){
        Mono<ResponseEntity<?>> monoresponse = this.geoWeatherService.getTodaysDayForecast();
        return monoresponse;

    }

}
