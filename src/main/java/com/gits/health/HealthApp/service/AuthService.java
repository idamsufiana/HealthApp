package com.gits.health.HealthApp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Value("${satusehat.token}")
    private String authUrl;

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${client_credentials}")
    private String client_credentials;

    private final RestTemplate restTemplate;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAccessToken() {

        // Request body parameters
        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("grant_type", client_credentials);
        bodyParams.put("client_id", clientId);
        bodyParams.put("client_secret", clientSecret);

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create request entity with headers and body
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(bodyParams, headers);

        // Exchange request with server to fetch response
        ResponseEntity<Map> response = restTemplate.exchange(
                authUrl,
                HttpMethod.POST,
                requestEntity,
                Map.class
        );

        // Extract and return the access token
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().get("access_token").toString();
        } else {
            throw new RuntimeException("Failed to fetch access token. Response: " + response);
        }
    }
}