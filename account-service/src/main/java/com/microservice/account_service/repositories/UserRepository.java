package com.microservice.account_service.repositories;

import com.microservice.account_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    private final JdbcTemplate jdbcTemplate;
//
//    public UserRepositories(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public int insertUser(String firstName, String lastName, String username, String password,
//            String gender, String phone, String email, String type, String status,
//            java.sql.Date dateOfBirth, Timestamp createdAt, Timestamp updatedAt) {
//        StringBuilder sql = new StringBuilder();
//        sql.append(
//                "INSERT INTO tbl_users (first_name, last_name, username, password, gender, phone, email, type, date_of_birth, status, created_at, updated_at) ");
//        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");
//        return jdbcTemplate.queryForObject(sql.toString(), Integer.class, firstName, lastName, username, password,
//                gender, phone, email, type, dateOfBirth, status, createdAt, updatedAt);
//    }
//
//    public int insertRole(Long id, String name, String description) {
//        StringBuilder sql = new StringBuilder();
//        sql.append(
//                "INSERT INTO tbl_role (id, name, description) ");
//        sql.append("VALUES (?, ?, ?) RETURNING id");
//        return jdbcTemplate.queryForObject(sql.toString(), Integer.class, id, name, description);
//    }
//
//    public int insertPermissions(Long id, String name, String description) {
//        StringBuilder sql = new StringBuilder();
//        sql.append(
//                "INSERT INTO tbl_permission (id, name, description) ");
//        sql.append("VALUES (?, ?, ?) RETURNING id");
//        return jdbcTemplate.queryForObject(sql.toString(), Integer.class, id, name, description);
//    }
//
//    public void insertUserRole(int userId, int roleId, Timestamp createdAt, Timestamp updatedAt) {
//        String sql = "INSERT INTO tbl_user_has_role (user_id, role_id, created_at, updated_at) VALUES (?, ?, ?, ?)";
//        jdbcTemplate.update(sql, userId, roleId, createdAt, updatedAt);
//    }
//
//    public void insertRolePermission(int userId, int roleId, Timestamp createdAt, Timestamp updatedAt) {
//        String sql = "INSERT INTO tbl_role_has_permission (user_id, role_id, created_at, updated_at) VALUES (?, ?, ?, ?)";
//        jdbcTemplate.update(sql, userId, roleId, createdAt, updatedAt);
//    }

    // public List<?> getListUser() {
    // StringBuilder sql = new StringBuilder();
    // sql.append(
    // "SELECT * FROM tbl_users");
    // return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
    // }
}
