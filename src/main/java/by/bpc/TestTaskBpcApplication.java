package by.bpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableFeignClients
public class TestTaskBpcApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestTaskBpcApplication.class, args);
    }
}
