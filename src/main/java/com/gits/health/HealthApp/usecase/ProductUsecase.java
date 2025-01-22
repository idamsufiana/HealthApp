package com.gits.health.HealthApp.usecase;

import com.gits.health.HealthApp.model.Category;
import com.gits.health.HealthApp.model.Product;
import com.gits.health.HealthApp.model.ProductResponse;
import com.gits.health.HealthApp.service.HttpService;
import com.gits.health.HealthApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductUsecase {

    @Autowired
    HttpService httpService;
    @Autowired
    ProductService productService;

    public ProductResponse getAndsave(String AtcCode, String token){

        ProductResponse product = new ProductResponse();
        try{
            product = httpService.getProducts(AtcCode, token);
            List<Product> products = mapToProductEntities(product);
            productService.saveList(products);

        }catch(Exception e){
            e.printStackTrace();
        }


        return product;
    }

    public List<Product> mapToProductEntities(ProductResponse productResponse) {
        if (productResponse.getItems() != null && productResponse.getItems().getData() != null) {
            return productResponse.getItems().getData().stream()
                    .map(product -> new Product(
                            product.getName(),
                            product.getProductTemplate() != null ? product.getProductTemplate().getKfaCode() : null,
                            product.getImage(),
                            product.getFixPrice() != null ? BigDecimal.valueOf(product.getFixPrice()) : null,
                            product.getNamaDagang(), // Assuming "namaDagang" as the description
                            mapToCategories(product.getFarmalkesType()) // Convert category information
                    ))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList(); // Return empty list if no data is available
    }

    private Set<Category> mapToCategories(ProductResponse.Product.FarmalkesType farmalkesType) {
        if (farmalkesType == null) {
            return Collections.emptySet();
        }
        Category category = new Category();
        category.setCode(farmalkesType.getCode());
        category.setName(farmalkesType.getName());
        return Set.of(category);
    }

}
