package com.microservice.authentication_service.repositories;

import com.microservice.authentication_service.model.GroupHasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupHasUserRepository extends JpaRepository<GroupHasUser, Long> {
}
