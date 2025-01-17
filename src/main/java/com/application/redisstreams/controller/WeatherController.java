package com.application.redisstreams.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.redisstreams.dto.WeatherData;
import com.application.redisstreams.service.WeatherStreamsConsumer;
import com.application.redisstreams.service.WeatherStreamsProducer;


@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherStreamsConsumer weatherStreamConsumer;

    private final WeatherStreamsProducer weatherStreamProducer;

    public WeatherController(WeatherStreamsConsumer weatherStreamConsumer, WeatherStreamsProducer weatherStreamProducer) {
        this.weatherStreamConsumer = weatherStreamConsumer;
        this.weatherStreamProducer = weatherStreamProducer;
    }

    @PostMapping
    public String publish(@RequestBody WeatherData weather) {
        weatherStreamProducer.publish(weather);
        return "Published the data successfully";
    }

    @GetMapping
    public String displayData() {
        return weatherStreamConsumer.displayData();
    }
}
