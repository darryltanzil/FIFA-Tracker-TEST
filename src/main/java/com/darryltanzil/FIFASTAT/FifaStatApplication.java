package com.darryltanzil.FIFASTAT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
public class FifaStatApplication {
    private static final Logger log = LoggerFactory.getLogger(FifaStatApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(FifaStatApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(myInterceptor()));
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestInterceptor myInterceptor() {
        String apiKey = System.getenv("APIKEY");

        return (request, body, execution) -> {
            request.getHeaders().add("X-AUTH-TOKEN", apiKey);
            System.out.print("API KEY IS " + apiKey + "TEST");
            return execution.execute(request, body);
        };
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Player stats = restTemplate.getForObject(
                    "https://futdb.app/api/players/1", Player.class);
            log.info(stats.toString());
        };
    }
}
