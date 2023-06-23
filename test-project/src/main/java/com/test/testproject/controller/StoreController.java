package com.test.testproject.controller;

import com.test.testproject.dto.store.StoreDTO;
import com.test.testproject.model.Store;
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
    public ResponseEntity<Page<Store>> getAllStores(Pageable pageable)
    {
        return ResponseEntity.ok(storeService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Integer id)
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
    public ResponseEntity<HttpStatus> updateStore(@RequestBody @Valid StoreDTO request,
                                             @PathVariable Integer id)
    {
        storeService.update(id, request);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
