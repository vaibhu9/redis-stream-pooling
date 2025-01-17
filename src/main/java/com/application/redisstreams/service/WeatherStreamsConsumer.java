package com.application.redisstreams.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

import com.application.redisstreams.dto.WeatherData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeatherStreamsConsumer implements StreamListener<String, ObjectRecord<String, WeatherData>> {

    private Map<String, WeatherData> weatherDataMap = new HashMap<>();

    @Override
    public void onMessage(ObjectRecord<String, WeatherData> message) {
        log.info("Received message: {}", message.getValue());
        weatherDataMap.put(message.getId().getValue(), message.getValue());
    }

    public String displayData() {
        return "Received Data: "+weatherDataMap.toString();
    } 

}
