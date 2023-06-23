package com.test.testproject.service;

import com.test.testproject.dto.product.ProductRequest;
import com.test.testproject.model.Product;
import com.test.testproject.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService
{
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final StoreService storeService;

    public ProductService(ProductRepository productRepository, ModelMapper mapper, StoreService storeService)
    {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.storeService = storeService;
    }

    @Transactional
    public void create(ProductRequest request)
    {
        Product newProduct = mapper.map(request, Product.class);
        newProduct.setStore(storeService.getById(request.getStoreId())); // !!

        productRepository.save(newProduct);
    }

    @Transactional
    public void update(Integer id, ProductRequest request)
    {
        Product updatedProduct = mapper.map(request, Product.class);
        updatedProduct.setStore(storeService.getById(request.getStoreId())); // !!
        updatedProduct.setId(id);

        productRepository.save(updatedProduct);
    }

    @Transactional
    public void deleteById(Integer id)
    {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Product> getAll(Pageable pageable)
    {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Product getById(Integer id)
    {
        return productRepository.findById(id).orElse(null); // !!
    }
}
