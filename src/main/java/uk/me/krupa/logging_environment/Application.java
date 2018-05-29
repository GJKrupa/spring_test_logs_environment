package uk.me.krupa.logging_environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan("uk.me.krupa.logging_environment")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
