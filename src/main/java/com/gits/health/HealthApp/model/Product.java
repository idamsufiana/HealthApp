package com.gits.health.HealthApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity(name = "product")
public class Product extends CrudEntity{

    private String name;

    @Column(unique = true)
    private String sku;

    private String image;

    private BigDecimal price;

    private String description;

    @ManyToMany
    private Set<Category> categories;

    public Product(String name, String sku, String image, BigDecimal price, String description, Set<Category> categories) {

        this.name = name;
        this.sku = sku;
        this.image = image;
        this.price = price;
        this.description = description;
        this.categories = categories;
    }

    public Product(){

    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                '}';
    }
}
