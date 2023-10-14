package by.bpc.config;

import by.bpc.interceptor.CRC32FeignInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class FeignConfiguration {
    @Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet servlet = new DispatcherServlet();
        servlet.setThreadContextInheritable(true);
        return servlet;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new CRC32FeignInterceptor();
    }

}
