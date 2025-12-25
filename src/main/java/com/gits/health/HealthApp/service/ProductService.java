package com.gits.health.HealthApp.service;

import com.gits.health.HealthApp.model.CrudEntity;
import com.gits.health.HealthApp.model.Product;
import com.gits.health.HealthApp.model.dto.ProductDto;
import com.gits.health.HealthApp.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Optional<Product> get(Long id){
        return productRepository.findById(id);
    }

    public Product create(ProductDto dto){
        return createFromDto(dto);
    }

    public Product createFromDto(ProductDto dto){
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        CrudEntity crudEntity = (CrudEntity)product;
        crudEntity.setCreatedDate(new Date());
        productRepository.save(product);
        return product;
    }

    public void delete(Product id){
        this.productRepository.delete(id);
    }

    @Transactional
    public void saveList(List<Product> products) {
        int batchSize = 500;
        for (int i = 0; i < products.size(); i += batchSize) {
            int end = Math.min(i + batchSize, products.size());
            productRepository.saveAll(products.subList(i, end));
            productRepository.flush();
        }
    }

    public List<Product> findBySKU(String sku){
        List<Product> productsList = new ArrayList<>();
        try {
            productsList = productRepository.findBySKu(sku);

        }catch(Exception e){
            e.printStackTrace();
        }

        return productsList;
    }


}
