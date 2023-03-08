package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Privilege;
import com.mii.server.repositories.PrivilegeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PrivilegeService {
    
    private PrivilegeRepository privilegeRepository;

    public List<Privilege> getAll() {
        return privilegeRepository.findAll();
    }

    public Privilege getById(Integer id) {
        return privilegeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Privilege id not found !!!"));
    }

    public Privilege create(Privilege privilege) {
        if (privilegeRepository.findByName(privilege.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name is already exists !!!");
        }
        return privilegeRepository.save(privilege);
    }

    public Privilege update(Integer id, Privilege privilege) {
        getById(id);
        privilege.setId(id);
        return privilegeRepository.save(privilege);
    }

    public Privilege delete(Integer id) {
        Privilege privilege = getById(id);
        privilegeRepository.delete(privilege);
        return privilege;
    }
}
