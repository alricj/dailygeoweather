package com.interview.geoweather.service;

import com.interview.geoweather.model.ForecastPeriod;
import com.interview.geoweather.model.WeatherForecast;
import com.interview.geoweather.model.WeatherForecastResponse;
import com.interview.geoweather.util.WeatherConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Map;

@Service
public class GeoWeatherService {

    private final WebClient webClient;

    public GeoWeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(WeatherConstants.URI_HOST ).build();
    }


    public Mono<ResponseEntity<?>> getTodaysDayForecast() {
        return webClient.get()
                .uri(WeatherConstants.ENDPOINT_URl)
                .retrieve()
                .bodyToMono(WeatherForecast.class)
                .flatMapIterable(response-> response.getProperties().getPeriods())
                .filter(period -> LocalDate.parse(period.getStartTime(), WeatherConstants.formatter).isEqual(LocalDate.now()))
                .map(this::mapForecastResponse)
                .collectList()
                .map(daily -> {
                    return ResponseEntity.ok().body(Map.of("daily", daily));
                });
        }

        private  WeatherForecastResponse mapForecastResponse(ForecastPeriod period){
            WeatherForecastResponse weatherForecastResponse = new WeatherForecastResponse();
            weatherForecastResponse.setDayName(period.getName());
            weatherForecastResponse.setForecastBlurp(period.getShortForecast());
            weatherForecastResponse.setTempHighCelsius(period.getTemperature());

            return weatherForecastResponse;

        }

        @Deprecated
        public Mono<WeatherForecastResponse> _getTodaysDayForecast() {
            return webClient.get()
                    .uri(WeatherConstants.ENDPOINT_URl)
                    .retrieve()
                    .bodyToMono(WeatherForecast.class)
                    .flatMap(response-> {
                        ForecastPeriod forecastPeriod = response.getProperties().getPeriods().get(0);
                        WeatherForecastResponse weatherForecastResponse = new WeatherForecastResponse();
                        weatherForecastResponse.setDayName(forecastPeriod.getName());
                        weatherForecastResponse.setForecastBlurp(forecastPeriod.getShortForecast());
                        weatherForecastResponse.setTempHighCelsius(forecastPeriod.getTemperature());
                        return Mono.just(weatherForecastResponse);
                    });
        }

}
