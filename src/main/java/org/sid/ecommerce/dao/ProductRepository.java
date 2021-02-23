package org.sid.ecommerce.dao;

import org.sid.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("*")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    @RestResource(path = "/selectedProducts")
    public List<Product> findBySelectedIsTrue();

    @RestResource(path = "/productsByKeyword")
    public List<Product> findByNameContains(String keyword);

    @RestResource(path = "/PromotionProducts")
    public List<Product> findByPromotionIsTrue();

    @RestResource(path = "/AvailableProducts")
    public List<Product> findByAvailableIsTrue();
}
