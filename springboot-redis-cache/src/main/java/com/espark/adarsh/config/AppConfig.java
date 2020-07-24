package com.espark.adarsh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class AppConfig {

    @Autowired
    AppProps appProps;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(appProps.getHost());
        factory.setPort(appProps.getPort());
        factory.setUsePool(true);
        factory.getPoolConfig().setMaxIdle(30);
        factory.getPoolConfig().setMinIdle(10);
        return factory;
    }

    @Bean
    public RedisTemplate redisTemplate(@Autowired JedisConnectionFactory jedisConnectionFactory) {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer( new StringRedisSerializer() );
        template.setEnableTransactionSupport(true);
        template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        return template;
    }
}