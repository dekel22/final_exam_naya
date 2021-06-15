package com.epam.spark.exam.conf;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dekel Levitan
 */
@Configuration
public class SparkConfigForSpring {

    @Bean
    public SparkSession sparkSessionDev() {
        return SparkSession.builder().master("local[*]").appName("taxi").getOrCreate();
    }

}



