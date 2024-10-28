package com.microservice.authentication_service.repositories;

import com.microservice.authentication_service.model.UserHasRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserHasRoleRepository extends JpaRepository<UserHasRole, Long> {
}
