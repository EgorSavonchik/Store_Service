package com.test.testproject.service;

import com.test.testproject.dto.worker.WorkerRequest;
import com.test.testproject.dto.worker.WorkerResponse;
import com.test.testproject.exeption.EntityNotFoundException;
import com.test.testproject.model.Worker;
import com.test.testproject.repository.StoreRepository;
import com.test.testproject.repository.WorkerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkerService
{
    private final WorkerRepository workerRepository;
    private final StoreRepository storeRepository;
    private final ModelMapper mapper;

    public WorkerService(WorkerRepository workerRepository, StoreRepository storeRepository, ModelMapper mapper)
    {
        this.workerRepository = workerRepository;
        this.storeRepository = storeRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void create(WorkerRequest request)
    {
        Worker newWorker = mapper.map(request, Worker.class);
        newWorker.setPlaceOfWork(storeRepository.findById(request.getPlaceOfWorkId()).orElse(null));

        workerRepository.save(newWorker);
    }

    @Transactional
    public WorkerResponse update(Integer id, WorkerRequest request)
    {
        workerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Worker not found"));

        Worker updatedWorker = mapper.map(request, Worker.class);
        updatedWorker.setPlaceOfWork(storeRepository.findById(request.getPlaceOfWorkId()).orElse(null));
        updatedWorker.setId(id);

        return mapper.map(workerRepository.save(updatedWorker), WorkerResponse.class);
    }

    @Transactional
    public void deleteById(Integer id)
    {
        workerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<WorkerResponse> getAll(Pageable pageable)
    {
        return workerRepository.findAll(pageable).map(obj -> mapper.map(obj, WorkerResponse.class));
    }

    @Transactional(readOnly = true)
    public WorkerResponse getById(Integer id)
    {
        return mapper.map(
                workerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Worker not found"))
                , WorkerResponse.class);
    }
}
