package com.unimed.medstock.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unimed.medstock.entity.Product;
import com.unimed.medstock.enums.ProductType;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByType(ProductType type);
}