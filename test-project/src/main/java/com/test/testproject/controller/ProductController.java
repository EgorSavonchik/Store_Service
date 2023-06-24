package com.test.testproject.controller;

import com.test.testproject.dto.product.ProductRequest;
import com.test.testproject.dto.product.ProductResponse;
import com.test.testproject.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController
{
    private final ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<ProductResponse>> getAllProducts(Pageable pageable)
    {
        return ResponseEntity.ok(productService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id)
    {
        return ResponseEntity.ok(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeProductById(@PathVariable Integer id)
    {
        productService.deleteById(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createWorker(@RequestBody @Valid ProductRequest request)
    {
        productService.create(request);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody @Valid ProductRequest request,
                                             @PathVariable Integer id)
    {
        return ResponseEntity.ok(productService.update(id, request));
    }
}
