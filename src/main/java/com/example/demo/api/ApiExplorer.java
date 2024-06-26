package com.example.demo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
@SpringBootApplication
@Slf4j
public class ApiExplorer implements CommandLineRunner {
    @Value("${chargeinfo.service.key}")
    private String serviceKey;
    public static void main(String[] args) {
        SpringApplication.run(ApiExplorer.class, args);
    }

    @Override
    public void run(String... args) {

        try {

            String pageNo = "1";
            String numOfRows = "50000";
            String zcode = "11";
            String zscode = "11680";
//            String statId = "ME183119";

            String url = "http://apis.data.go.kr/B552584/EvCharger/getChargerInfo" +
                    "?serviceKey=" + serviceKey +
                    "&pageNo=" + URLEncoder.encode(pageNo, "UTF-8") +
                    "&numOfRows=" + URLEncoder.encode(numOfRows, "UTF-8") +
                    "&zcode=" + URLEncoder.encode(zcode, "UTF-8") +
                    "&zscode=" + URLEncoder.encode(zscode, "UTF-8");
//                    "&statId=" + URLEncoder.encode(statId, "UTF-8");

            log.info("url: {}", url);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                System.out.println("Response code: " + responseEntity.getStatusCode());
                System.out.println("Response body: " + responseEntity.getBody());
            } else {
                System.out.println("Error occurred! Response code: " + responseEntity.getStatusCode());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}