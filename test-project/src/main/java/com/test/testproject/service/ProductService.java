package com.test.testproject.service;

import com.test.testproject.dto.product.ProductRequest;
import com.test.testproject.dto.product.ProductResponse;
import com.test.testproject.exeption.EntityNotFoundException;
import com.test.testproject.model.Product;
import com.test.testproject.repository.ProductRepository;
import com.test.testproject.repository.StoreRepository;
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
    private final StoreRepository storeRepository;

    public ProductService(ProductRepository productRepository, ModelMapper mapper, StoreRepository storeRepository)
    {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.storeRepository = storeRepository;
    }

    @Transactional
    public void create(ProductRequest request)
    {
        Product newProduct = mapper.map(request, Product.class);
        newProduct.setStore(storeRepository.findById(request.getStoreId()).orElseThrow(() ->
                new EntityNotFoundException(request.getStoreId(), "Store not found")));

        productRepository.save(newProduct);
    }

    @Transactional
    public ProductResponse update(Integer id, ProductRequest request)
    {
        productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Product not found"));

        Product updatedProduct = mapper.map(request, Product.class);
        updatedProduct.setStore(storeRepository.findById(request.getStoreId()).orElseThrow(() ->
                new EntityNotFoundException(request.getStoreId(), "Store not found")));
        updatedProduct.setId(id);

        return mapper.map(productRepository.save(updatedProduct), ProductResponse.class);
    }

    @Transactional
    public void deleteById(Integer id)
    {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> getAll(Pageable pageable)
    {
        return productRepository.findAll(pageable).map(obj -> mapper.map(obj, ProductResponse.class));
    }

    @Transactional(readOnly = true)
    public ProductResponse getById(Integer id)
    {
        return mapper.map(
                productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Product not found")),
                ProductResponse.class);
    }
}
