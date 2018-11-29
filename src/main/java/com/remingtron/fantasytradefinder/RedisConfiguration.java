package com.remingtron.fantasytradefinder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

    @Bean
    public JedisConnectionFactory redisConnectionFactory() throws URISyntaxException {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        String redisUrlEnvironmentVariable = System.getenv("REDIS_URL");
        if (!StringUtils.isEmpty(redisUrlEnvironmentVariable)) {
            URI redisUrl = new URI(redisUrlEnvironmentVariable);
            redisStandaloneConfiguration.setHostName(redisUrl.getHost());
            redisStandaloneConfiguration.setPort(redisUrl.getPort());
        }
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(JedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
