package com.gits.health.HealthApp.controller;

import com.gits.health.HealthApp.common.CommonService;
import com.gits.health.HealthApp.service.AuthService;
import com.gits.health.HealthApp.service.ProductService;
import com.gits.health.HealthApp.usecase.ProductUsecase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping({"Gits/health"})
@Slf4j
public class HealthController extends BaseController {
    @Autowired
    ProductUsecase productUsecase;

    @Autowired
    ProductService productService;

    @Autowired
    AuthService authService;

    @Autowired
    CommonService commonService;

    @GetMapping({"/{atcCode}"})
    public ResponseEntity<Object> get(@PathVariable String atcCode) throws Throwable {
        String token = authService.getAccessToken();
        return this.success(productUsecase.getAndsave(atcCode, token));
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadProductImage(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        // Upload file to S3
        String fileUri = commonService.uploadFile(file);

        return ResponseEntity.ok("File uploaded successfully: " + fileUri);
    }

}
