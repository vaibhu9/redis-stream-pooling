package com.application.redisstreams.configs;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import com.application.redisstreams.dto.WeatherData;


@Configuration
public class RedisConfig {

   @Value("${weather-stream.key}")
   private String streamKey;

    @Bean
    public Subscription subscription(RedisConnectionFactory connectionFactory, StreamListener<String, ObjectRecord<String, WeatherData>> streamListener) {
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, WeatherData>> options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofMillis(100))
                .targetType(WeatherData.class)
                .batchSize(1)
                .build();

        StreamMessageListenerContainer<String, ObjectRecord<String, WeatherData>> container = StreamMessageListenerContainer
                .create(connectionFactory, options);

        Subscription subscription = container.receive(
                StreamOffset.fromStart(streamKey),
                streamListener
        );

        container.start();
        return subscription;
    }
}
