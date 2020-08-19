package com.gabryellr.localdate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;

@Configuration
public class Clock {

    @Bean
    public java.time.Clock clock() {
        return java.time.Clock.system(ZoneId.of("America/Sao_Paulo"));
    }

}