package com.corepersistence.service.promociones

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MyConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }
}