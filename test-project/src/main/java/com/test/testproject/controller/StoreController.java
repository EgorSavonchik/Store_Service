package com.test.testproject.controller;

import com.test.testproject.dto.product.ProductResponse;
import com.test.testproject.dto.store.StoreDTO;
import com.test.testproject.dto.worker.WorkerResponse;
import com.test.testproject.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
public class StoreController
{
    private final StoreService storeService;
    public StoreController(StoreService storeService)
    {
        this.storeService = storeService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<StoreDTO>> getAllStores(Pageable pageable)
    {
        return ResponseEntity.ok(storeService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Integer id)
    {
        return ResponseEntity.ok(storeService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeStoreById(@PathVariable Integer id)
    {
        storeService.deleteById(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createStore(@RequestBody @Valid StoreDTO request)
    {
        storeService.create(request);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(@RequestBody @Valid StoreDTO request,
                                                       @PathVariable Integer id)
    {
        return ResponseEntity.ok(storeService.update(id, request));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Page<ProductResponse>> getStoreProducts(@PathVariable Integer id, Pageable pageable)
    {
        return ResponseEntity.ok(storeService.getStoreProducts(id, pageable));
    }

    @GetMapping("/{id}/workers")
    public ResponseEntity<Page<WorkerResponse>> getStoreWorkers(@PathVariable Integer id, Pageable pageable)
    {
        return ResponseEntity.ok(storeService.getStoreWorkers(id, pageable));
    }
}
