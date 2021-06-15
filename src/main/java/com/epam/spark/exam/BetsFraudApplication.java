package com.epam.spark.exam;

import com.epam.spark.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class BetsFraudApplication {
       public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BetsFraudApplication.class, args);
    }
}
