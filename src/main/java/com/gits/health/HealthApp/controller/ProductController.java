package com.gits.health.HealthApp.controller;



import com.gits.health.HealthApp.common.Secured;
import com.gits.health.HealthApp.constant.ApplicationEnum;
import com.gits.health.HealthApp.model.Product;
import com.gits.health.HealthApp.model.dto.ProductDto;
import com.gits.health.HealthApp.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"Gits/product"})
@Slf4j
public class ProductController extends BaseController {

    @Autowired
    ProductService productService;

    @Secured({ApplicationEnum.Group.Admin})
    @GetMapping({"/{id}"})
    public ResponseEntity<Object> get(@PathVariable Long id) throws Throwable {
        Optional<Product> optionalE = productService.get(id);
        return optionalE.isPresent() ? this.success(optionalE.get()) : this.error(HttpStatus.NOT_FOUND, "Object not found");
    }

    @GetMapping({"/{sku}"})
    public ResponseEntity<Object> get(@PathVariable String sku) throws Throwable {
        List<Product> list = productService.findBySKU(sku);
        return !list.isEmpty() ? this.success(list) : this.error(HttpStatus.NOT_FOUND, "Object not found");
    }

    @PostMapping({""})
    public ResponseEntity<Object> create(@RequestBody @Valid ProductDto dto) throws Throwable {
        return this.success(productService.create(dto));
    }

    @PostMapping({"/{id}"})
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid ProductDto dto) throws Throwable {
        Optional<Product> optionalE = productService.get(id);
        if (optionalE.isPresent()) {
            Product updatedEntity = productService.create(dto);
            return this.success(updatedEntity);
        } else {
            return this.error(HttpStatus.NOT_FOUND, "Object not found");
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Object> delete(@PathVariable Long id) throws Throwable {
        Optional<Product> optionalE = productService.get(id);
        if (optionalE.isPresent()) {
            productService.delete(optionalE.get());
            return this.success("Object was deleted");
        } else {
            return this.error(HttpStatus.NOT_FOUND, "Object not found");
        }
    }
}
