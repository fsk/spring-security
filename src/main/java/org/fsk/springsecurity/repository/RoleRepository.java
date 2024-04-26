package org.fsk.springsecurity.repository;

import org.fsk.springsecurity.entities.Role;
import org.fsk.springsecurity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
