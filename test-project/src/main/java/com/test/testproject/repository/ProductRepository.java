package com.test.testproject.repository;

import com.test.testproject.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
    @Query("SELECT pr FROM Product pr WHERE (:storeId IS NULL OR pr.store.id = :storeId)")
    Page<Product> findAllByStoreId(@Param("storeId") Integer storeId, Pageable pageable);
}
