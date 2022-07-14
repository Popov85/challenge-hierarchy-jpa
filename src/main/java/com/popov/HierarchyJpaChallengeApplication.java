package com.popov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

/**
 * @author Andrey P.
 */
@SpringBootApplication
public class HierarchyJpaChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HierarchyJpaChallengeApplication.class, args);

        RestTemplate rt = new RestTemplate();
    }

}
