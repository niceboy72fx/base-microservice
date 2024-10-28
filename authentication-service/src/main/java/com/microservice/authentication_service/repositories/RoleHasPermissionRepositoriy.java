package com.microservice.authentication_service.repositories;

import com.microservice.authentication_service.model.RoleHasPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleHasPermissionRepositoriy extends JpaRepository<RoleHasPermission, Long> {
}
