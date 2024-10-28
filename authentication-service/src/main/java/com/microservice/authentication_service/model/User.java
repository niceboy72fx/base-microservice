package com.microservice.authentication_service.model;

import com.microservice.authentication_service.common.Gender;
import com.microservice.authentication_service.common.UserStatus;
import com.microservice.authentication_service.common.UserType;
import com.microservice.authentication_service.model.BaseEntity;
import com.microservice.authentication_service.model.GroupHasUser;
import com.microservice.authentication_service.model.UserHasRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "tbl_user")
public class User extends BaseEntity<Long> implements UserDetails, Serializable {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    public User(String email, String firstName, String lastName, Date dateOfBirth, Gender gender, String phone, String username, String password, UserType type, UserStatus status, Set<UserHasRole> roles, Set<GroupHasUser> groups) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.type = type;
        this.status = status;
        this.roles = roles;
        this.groups = groups;
    }

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "type")
    private UserType type;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status")
    private UserStatus status;

    @OneToMany(mappedBy = "user")
    private Set<UserHasRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<GroupHasUser> groups = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roleList = this.roles.stream().map(role -> role.getRoles().getName()).toList();
        return roleList.stream().map(SimpleGrantedAuthority::new).toList();
    }

    /**
     * @return 
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @return 
     */
    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !UserStatus.LOCKED.equals(status);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserStatus.ACTIVE.equals(status);    }
}
