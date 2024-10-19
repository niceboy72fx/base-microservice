package com.microservice.authentication_service.model;

import com.microservice.authentication_service.model.BaseEntity;
import com.microservice.authentication_service.model.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "tbl_role_has_permission")
public class RoleHasPermission   extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roles;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

}
