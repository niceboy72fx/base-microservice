package com.microservice.account_service.services;

import com.microservice.account_service.controller.request.PwdChangeRequestDTO;
import com.microservice.account_service.controller.request.UserCreationRequestDTO;
import com.microservice.account_service.controller.request.UserUpdateDTO;
import com.microservice.account_service.controller.response.UserResponseDTO;

import java.util.List;

public interface AccountService {

    long addUser(UserCreationRequestDTO dto);

    void updateUser(UserUpdateDTO dto);

    void changePassword(PwdChangeRequestDTO dto);

    void deleteUser(long userId);

    UserResponseDTO getUserDetails(long userId);

    List<UserResponseDTO> getUsers(int page, int size, String sort);
}
