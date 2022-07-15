package com.demo.data.data.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(Objects.requireNonNull(env.getProperty("redis.hostname")));
        redisStandaloneConfiguration.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("redis.port"))));
        redisStandaloneConfiguration.setPassword(Objects.requireNonNull(env.getProperty("redis.password")));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean(name = "publisher-cache")
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean(name = "pubs-db")
    public DataSource getSqlDbDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceBuilder.url(env.getProperty("pubs-db.uri"));
        dataSourceBuilder.username(env.getProperty("pubs-db.user"));
        dataSourceBuilder.password(env.getProperty("pubs-db.password"));
        return dataSourceBuilder.build();
    }

}
