package com.test.testproject.service;

import com.test.testproject.dto.product.ProductResponse;
import com.test.testproject.dto.store.StoreDTO;
import com.test.testproject.dto.worker.WorkerResponse;
import com.test.testproject.exeption.EntityNotFoundException;
import com.test.testproject.model.Store;
import com.test.testproject.repository.ProductRepository;
import com.test.testproject.repository.StoreRepository;
import com.test.testproject.repository.WorkerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreService
{
    private final StoreRepository storeRepository;
    private final WorkerRepository workerRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public StoreService(StoreRepository storeRepository, WorkerRepository workerRepository, ProductRepository productRepository, ModelMapper mapper)
    {
        this.storeRepository = storeRepository;
        this.workerRepository = workerRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void create(StoreDTO storeDTO)
    {
        Store newStore = mapper.map(storeDTO, Store.class);

        storeRepository.save(newStore);
    }

    @Transactional
    public StoreDTO update(Integer id, StoreDTO storeDTO)
    {
        storeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Store not found"));

        Store updatedStore = mapper.map(storeDTO, Store.class);
        updatedStore.setId(id);

        return mapper.map(storeRepository.save(updatedStore), StoreDTO.class);
    }

    @Transactional
    public void deleteById(Integer id)
    {
        storeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<StoreDTO> getAll(Pageable pageable)
    {
        return storeRepository.findAll(pageable).map(obj -> mapper.map(obj, StoreDTO.class));
    }

    @Transactional(readOnly = true)
    public StoreDTO getById(Integer id)
    {
        return mapper.map(storeRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Store not found")),
                StoreDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> getStoreProducts(Integer storeId, Pageable pageable)
    {
        return productRepository.findAllByStoreId(storeId, pageable).map(obj -> mapper.map(obj, ProductResponse.class));
    }

    @Transactional(readOnly = true)
    public Page<WorkerResponse> getStoreWorkers(Integer storeId, Pageable pageable)
    {
        return workerRepository.findAllByStoreId(storeId, pageable).map(obj -> mapper.map(obj, WorkerResponse.class));
    }
}
