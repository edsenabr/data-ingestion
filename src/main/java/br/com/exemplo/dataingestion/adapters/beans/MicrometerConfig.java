package br.com.exemplo.dataingestion.adapters.beans;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrometerConfig {

    @Bean
    public SimpleMeterRegistry getMeter()
    {
        return new SimpleMeterRegistry();
    }
}
