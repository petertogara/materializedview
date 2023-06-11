package com.petmuc.materializedview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MaterializedViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaterializedViewApplication.class, args);
    }

}
