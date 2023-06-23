package com.test.testproject.service;

import com.test.testproject.dto.product.ProductRequest;
import com.test.testproject.dto.product.ProductResponse;
import com.test.testproject.exeption.EntityNotFoundException;
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
        newProduct.setStore(storeService.getById(request.getStoreId()));

        productRepository.save(newProduct);
    }

    @Transactional
    public ProductResponse update(Integer id, ProductRequest request)
    {
        productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Product not found"));

        Product updatedProduct = mapper.map(request, Product.class);
        updatedProduct.setStore(storeService.getById(request.getStoreId()));
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
