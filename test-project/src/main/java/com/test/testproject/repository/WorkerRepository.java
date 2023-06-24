package com.test.testproject.repository;

import com.test.testproject.model.Product;
import com.test.testproject.model.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkerRepository extends JpaRepository<Worker, Integer>
{
    @Query("SELECT w FROM Worker w WHERE (:storeId IS NULL OR w.placeOfWork.id = :storeId)")
    Page<Worker> findAllByStoreId(@Param("storeId") Integer storeId, Pageable pageable);
}
