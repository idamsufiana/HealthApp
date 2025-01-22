package com.gits.health.HealthApp.repository;

import com.gits.health.HealthApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select d from product d where d.sku = ?1")
    List<Product> findBySKu(String sku);


}
