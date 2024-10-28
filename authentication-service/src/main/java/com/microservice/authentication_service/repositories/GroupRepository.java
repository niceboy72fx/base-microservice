package com.microservice.authentication_service.repositories;

import com.microservice.authentication_service.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group getById (Long Id);
}
