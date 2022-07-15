package com.github.wandirpereira;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalConfiguration {

    @Bean(name="applicationName")
    public String applicationName(){
        return "Sistema de Vendas";
    }

}
