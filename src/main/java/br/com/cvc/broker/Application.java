package br.com.cvc.broker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
7import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.cvc")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
