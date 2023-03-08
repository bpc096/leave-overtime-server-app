package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.TypeLeave;
import com.mii.server.repositories.TypeLeaveRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TypeLeaveService {

    private TypeLeaveRepository typeLeaveRepository;

    public List<TypeLeave> getAll() {
        return typeLeaveRepository.findAll();
    }

    public TypeLeave getById(Integer id) {
        return typeLeaveRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TypeLeave id not found!!!"));
    }

    public TypeLeave create(TypeLeave typeLeave) {
        if (typeLeaveRepository.findByName(typeLeave.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "TypeLeave name is already exists...");
        }
        return typeLeaveRepository.save(typeLeave);
    }

    public TypeLeave update(Integer id, TypeLeave typeLeave) {
        getById(id);
        typeLeave.setId(id);
        if (typeLeaveRepository.findByName(typeLeave.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "TypeLeave name is already exists...");
        }
        return typeLeaveRepository.save(typeLeave);
    }

    public TypeLeave delete(Integer id) {
        TypeLeave typeleave = getById(id);
        typeLeaveRepository.delete(typeleave);
        return typeleave;
    }
}