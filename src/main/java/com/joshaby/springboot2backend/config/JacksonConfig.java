package com.joshaby.springboot2backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joshaby.springboot2backend.entities.PagamentoComBoleto;
import com.joshaby.springboot2backend.entities.PagamentoComCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {

            public void configure(ObjectMapper mapper) {
                mapper.registerSubtypes(PagamentoComCartao.class);
                mapper.registerSubtypes(PagamentoComBoleto.class);
                super.configure(mapper);
            }
        };
        return builder;
    }
}
