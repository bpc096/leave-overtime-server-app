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
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role id not found!!!"));
    }

    public TypeLeave create(TypeLeave typeLeave) {
        if (typeLeaveRepository.findByName(typeLeave.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name is already exists...");
        }
        return roleRepository.save(role);
    }

    public Roles update(Integer id, Roles role) {
        getById(id);
        role.setId(id);
        if (roleRepository.findByName(role.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name is already exists...");
        }
        return roleRepository.save(role);
    }

    public Roles delete(Integer id) {
        Roles role = getById(id);
        roleRepository.delete(role);
        return role;
    }
}
