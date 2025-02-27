package com.gits.health.HealthApp.controller;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gits.health.HealthApp.config.SecurityConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
public class AuthController {

    @Autowired
    private SecurityConfig config;

    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping(value = "/login")
    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirectUri = "http://localhost:8080/callback";
        String authorizeUrl = authenticationController.buildAuthorizeUrl((javax.servlet.http.HttpServletRequest) request, (javax.servlet.http.HttpServletResponse) response, redirectUri)
                .withScope("openid email")
                .build();
        response.sendRedirect(authorizeUrl);
    }
    @GetMapping(value="/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws IdentityVerificationException, IOException {
        Tokens tokens = authenticationController.handle((javax.servlet.http.HttpServletRequest) request, (javax.servlet.http.HttpServletResponse) response);

        DecodedJWT jwt = JWT.decode(tokens.getIdToken());
        TestingAuthenticationToken authToken2 = new TestingAuthenticationToken(jwt.getSubject(),
                jwt.getToken());
        authToken2.setAuthenticated(true);

        SecurityContextHolder.getContext().setAuthentication(authToken2);
        response.sendRedirect(request.getContextPath() + "/");
    }
}
