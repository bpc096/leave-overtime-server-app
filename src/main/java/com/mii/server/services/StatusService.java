package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Status;
import com.mii.server.repositories.StatusRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StatusService {
    
    private StatusRepository statusRepository;

    public List<Status> getAll() {
        return statusRepository.findAll();
    }

    public Status getById(Integer id) {
        return statusRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role id not found!!!"));
    }

    public Status create(Status status) {
        if (statusRepository.findByName(status.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name is already exists...");
        }
        return statusRepository.save(status);
    }

    public Status update(Integer id, Status status) {
        getById(id);
        status.setId(id);
        if (statusRepository.findByName(status.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name is already exists...");
        }
        return statusRepository.save(status);
    }

    public Status delete(Integer id) {
        Status status = getById(id);
        statusRepository.delete(status);
        return status;
    }
}
