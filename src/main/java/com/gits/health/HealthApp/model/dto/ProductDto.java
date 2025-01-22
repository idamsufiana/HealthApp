package com.gits.health.HealthApp.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class ProductDto {

    private String name;

    @Column(unique = true)
    private String sku;

    private String image;

    private BigDecimal price;

    private String description;

}
