package org.edu.fabs.javaparkinglot.auth.repository;

import org.edu.fabs.javaparkinglot.auth.entity.Role;
import org.edu.fabs.javaparkinglot.auth.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);

}
