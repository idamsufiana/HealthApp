package com.gits.health.HealthApp.repository;

import com.gits.health.HealthApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
