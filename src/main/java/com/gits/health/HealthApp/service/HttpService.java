package com.gits.health.HealthApp.service;

import com.gits.health.HealthApp.model.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class HttpService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${satusehat.product}")
    private String apiUrl;


    public ProductResponse getProducts(String atcCode, String token) {
        HttpHeaders httpHeaders = initHeaders(token);

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

            String url = UriComponentsBuilder
                    .fromHttpUrl(apiUrl)
                    .queryParam("page", 1)
                    .queryParam("size", 50)
                    .queryParam("atc_code", atcCode)
                    .queryParam("level", 1)
                    .toUriString();

        ResponseEntity<ProductResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                ProductResponse.class
        );

        return response.getBody();
    }

    private HttpHeaders initHeaders(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

}
