package com.application.redisstreams.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.application.redisstreams.dto.WeatherData;

@Service
public class WeatherStreamsProducer {

    @Value("${weather-stream.key}")
    private String streamKey;

    private final RedisTemplate<String, String> redisTemplate;

    public WeatherStreamsProducer(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(WeatherData weather) {
        ObjectRecord<String, WeatherData> record = ObjectRecord.create(streamKey, weather);
        redisTemplate.opsForStream().add(record);
    }
}
