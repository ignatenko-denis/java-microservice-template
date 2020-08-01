package com.sample.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "app")
@EnableAsync
@EnableScheduling
public class AppConfig {
    private String label;

    @PostConstruct
    private void log() {
        StringBuilder builder = new StringBuilder(40);

        builder.append("Application Configuration:").append("\n")
                .append("\tlabel: ").append(label).append("\n");

        log.info(builder.toString());
    }
}