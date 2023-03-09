package com.mii.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mii.server.models.User;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "update tb_user_role set role_id= ", nativeQuery = true)
    public List<User> changerole(String username);

    public Optional<User> findByUsername(String username);

    public Optional<User> findByUsernameOrEmployee_Email(String username, String email);
}
