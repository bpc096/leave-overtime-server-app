package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Privilege;
import com.mii.server.models.Role;
import com.mii.server.repositories.RoleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RoleService {
    private RoleRepository roleRepository;
    private PrivilegeService privilegeService;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getById(Integer id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role id not found!!!"));
    }


    public Role create(Role role) {
        if (roleRepository.findByName(role.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name is already exists...");
        }
        return roleRepository.save(role);
    }

    public Role update(Integer id, Role role) {
        getById(id);
        role.setId(id);
        if (roleRepository.findByName(role.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name is already exists...");
        }
        return roleRepository.save(role);
    }

    public Role delete(Integer id) {
        Role role = getById(id);
        roleRepository.delete(role);
        return role;
    }

    public Role addPrivilege(Integer id, Privilege privilege) {
        Role role = getById(id);
        List<Privilege> privileges = role.getPrivileges();
        privileges.add(privilegeService.getById(privilege.getId()));
        role.setPrivileges(privileges);
        return roleRepository.save(role);
    }
}
