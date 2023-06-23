package com.test.testproject.service;

import com.test.testproject.dto.store.StoreDTO;
import com.test.testproject.model.Store;
import com.test.testproject.repository.StoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreService
{
    private final StoreRepository storeRepository;
    private final ModelMapper mapper;

    public StoreService(StoreRepository storeRepository, ModelMapper mapper)
    {
        this.storeRepository = storeRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void create(StoreDTO storeDTO)
    {
        Store newStore = mapper.map(storeDTO, Store.class); // !!

        storeRepository.save(newStore);
    }

    @Transactional
    public void update(Integer id, StoreDTO storeDTO)
    {
        Store updatedStore = mapper.map(storeDTO, Store.class); // !!
        updatedStore.setId(id);

        storeRepository.save(updatedStore);
    }

    @Transactional
    public void deleteById(Integer id)
    {
        storeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Store> getAll(Pageable pageable)
    {
        return storeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Store getById(Integer id)
    {
        return storeRepository.findById(id).orElse(null); // !!
    }
}