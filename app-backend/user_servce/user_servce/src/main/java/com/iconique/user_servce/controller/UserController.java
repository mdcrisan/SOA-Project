package com.iconique.user_servce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.iconique.user_servce.dto.LoginData;
import com.iconique.user_servce.dto.RegisterRequest;
import com.iconique.user_servce.dto.UserRequest;
import com.iconique.user_servce.model.User;
import com.iconique.user_servce.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginData loginDara){
    	
    	ResponseEntity<String> tokenResponse = userService.obtainToken(loginDara);
    	
    	return ResponseEntity.ok(tokenResponse.getBody());
    }
    
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) throws JsonMappingException, JsonProcessingException{
    	
    	ResponseEntity<String> tokenResponse = userService.registerUser(registerRequest);
    	
    	return ResponseEntity.ok(tokenResponse.getBody());
    }

    //POST API to REGISTER USER
    @PostMapping("creat-user")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest){
         return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    // GET API to GET USER
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
       return new ResponseEntity<>(userService.getUserById(Long.parseLong(userId)), HttpStatus.OK);
    }
    //PUT API to UPDATE USER
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId,
                                           @RequestBody UserRequest userRequest){
        return  new ResponseEntity<>(userService.updateUser(Long.parseLong(userId), userRequest),HttpStatus.OK);
    }

    // DELETE API to DELETE USER
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userId){
        userService.deleteUser(Long.parseLong(userId));
        return new ResponseEntity<>(Void.class, HttpStatus.OK);
    }
}
