package com.test.testproject.controller;

import com.test.testproject.dto.worker.WorkerRequest;
import com.test.testproject.dto.worker.WorkerResponse;
import com.test.testproject.model.Worker;
import com.test.testproject.service.WorkerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workers")
public class WorkerController
{
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService)
    {
        this.workerService = workerService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<WorkerResponse>> getAllWorkers(Pageable pageable)
    {
        return ResponseEntity.ok(workerService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkerResponse> getWorkerById(@PathVariable Integer id)
    {
        return ResponseEntity.ok(workerService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeWorkerById(@PathVariable Integer id)
    {
        workerService.deleteById(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createWorker(@RequestBody @Valid WorkerRequest request)
    {
        workerService.create(request);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkerResponse> update(@RequestBody @Valid WorkerRequest request,
                                             @PathVariable Integer id)
    {
        return ResponseEntity.ok(workerService.update(id, request));
    }
}
