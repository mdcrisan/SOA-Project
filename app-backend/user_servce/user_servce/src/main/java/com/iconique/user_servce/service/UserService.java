package com.iconique.user_servce.service;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.iconique.user_servce.dto.LoginData;
import com.iconique.user_servce.dto.RegisterRequest;
import com.iconique.user_servce.dto.UserRequest;
import com.iconique.user_servce.model.User;

public interface UserService {
    User createUser(UserRequest userRequest);

    User getUserById(Long userId);

    User updateUser(Long userId, UserRequest userRequest);

    void deleteUser(Long userId);

	ResponseEntity<String> obtainToken(LoginData loginDara);

	ResponseEntity<String> registerUser(RegisterRequest registerRequest)throws JsonMappingException, JsonProcessingException;
}
