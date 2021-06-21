package com.example.testmagic.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.ssssssss.magicapi.config.MagicDynamicDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "magic")
    @Qualifier("magic")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource magicDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "pm")
    @Qualifier("pm")
    @ConfigurationProperties(prefix = "spring.pm-datasource")
    public javax.sql.DataSource pmDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public MagicDynamicDataSource magicDynamicDataSource() {
        MagicDynamicDataSource dynamicDataSource = new MagicDynamicDataSource();
        dynamicDataSource.setDefault(magicDataSource());
        dynamicDataSource.add("pm", pmDataSource());
        return dynamicDataSource;
    }
}
