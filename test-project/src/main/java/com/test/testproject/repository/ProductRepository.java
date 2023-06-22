package com.test.testproject.repository;

import ch.qos.logback.core.model.INamedModel;
import com.test.testproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
