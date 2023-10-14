package by.bpc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {
    @Bean
    public java.util.logging.Logger javaUtilLogger() {
        return java.util.logging.Logger.getLogger("BPC-logger");
    }
}

