package com.iconique.user_servce.service.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iconique.user_servce.dto.Credentials;
import com.iconique.user_servce.dto.LoginData;
import com.iconique.user_servce.dto.RegisterKeycloakRequest;
import com.iconique.user_servce.dto.RegisterRequest;
import com.iconique.user_servce.dto.TokenResponse;
import com.iconique.user_servce.dto.UserRequest;
import com.iconique.user_servce.model.Address;
import com.iconique.user_servce.model.Name;
import com.iconique.user_servce.model.User;
import com.iconique.user_servce.repository.UserRepository;
import com.iconique.user_servce.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Value("${client.id}")
	String clientId;
	
	@Value("${grant.type}")
	String grantType;
	
	@Value("${cient.secret}")
	String clientSecret;
	
	@Value("${keycloak.url}")
	String keycloakUrl;
	
	@Value("${keycloak.usercreateurl}")
	String keycloakUserCreateUrl;
	
	@Value("${keycloak.adminuser}")
	String adminUser;
	
	@Value("${keycloak.adminpassword}")
	String adminPasswrd;

	@Autowired
	private RestTemplate restTemplate;
	

	@Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserRequest userRequest) {
        log.info("Create user {} start", userRequest.userName());
        Address address = Address.builder()
                .no(userRequest.no())
                .firstLine(userRequest.firstLine())
                .secondLine(userRequest.secondLine())
                .zipCode(userRequest.zipCode())
                .province(userRequest.province())
                .mobileNo(userRequest.mobileNo())
                .build();

        Name name = Name.builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .middleName(userRequest.middleName())
                .build();
        return userRepository.save(
                User.builder()
                        .password(userRequest.password())
                        .email(userRequest.email())
                        .userName(userRequest.userName())
                        .address(address)
                        .name(name)
                .build());
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            return userOptional.get();
        }else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User" +userId+ "not found");
    }

    @Override
    public User updateUser(Long userId, UserRequest userRequest) {
        log.info("Update user {} start", userRequest.userName());
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User updatableUser = userOptional.get();
            updatableUser.setUserName(userRequest.userName());
            updatableUser.setPassword(userRequest.password());
            updatableUser.setEmail(userRequest.email());

            Address updatableAddress = updatableUser.getAddress();
            updatableAddress.setNo(userRequest.no());
            updatableAddress.setFirstLine(userRequest.firstLine());
            updatableAddress.setSecondLine(userRequest.secondLine());
            updatableAddress.setProvince(userRequest.province());
            updatableAddress.setZipCode(userRequest.zipCode());
            updatableAddress.setMobileNo(userRequest.mobileNo());
            updatableUser.setAddress(updatableAddress);

            Name updatableName = updatableUser.getName();
            updatableName.setFirstName(userRequest.firstName());
            updatableName.setLastName(userRequest.lastName());
            updatableName.setMiddleName(userRequest.middleName());
            updatableUser.setName(updatableName);
            return userRepository.save(updatableUser);
        }else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User" +userId+ "not found to Update");

    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Delete user {} start", userId);
        userRepository.deleteById(userId);
    }

    

	public ResponseEntity<String> obtainToken(LoginData loginData) {
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username",loginData.getUsername());
        map.add("password",loginData.getPassword());
        map.add("client_id",clientId);
        map.add("grant_type",grantType);
        map.add("client_secret",clientSecret);
        
        HttpHeaders  headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		
		return restTemplate.postForEntity(keycloakUrl, request, String.class);
		
	}

	@Override
	public ResponseEntity<String> registerUser(RegisterRequest registerRequest) throws JsonMappingException, JsonProcessingException {
		
		RegisterKeycloakRequest keycloakRequest = new RegisterKeycloakRequest();
		BeanUtils.copyProperties(registerRequest, keycloakRequest);
		List<Credentials> credentialsList = new ArrayList<>();
		credentialsList.add(new Credentials("password",  registerRequest.getPassword(), false));
		keycloakRequest.setCredentials(credentialsList);
		keycloakRequest.setEnabled(true);
		LoginData loginData =  new LoginData();
		loginData.setUsername(adminUser);
		loginData.setPassword(adminPasswrd);
		ResponseEntity<String> adminTokenResponse = obtainToken(loginData);
		ObjectMapper mapper =  new ObjectMapper();
		
		TokenResponse response = mapper.readValue(adminTokenResponse.getBody(), TokenResponse.class);
        
        HttpHeaders  headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer "+response.getAccessToken());
        log.info("Request {}", keycloakRequest.toString());
        HttpEntity<RegisterKeycloakRequest> request = new HttpEntity<>(keycloakRequest, headers);
		
		return restTemplate.postForEntity(keycloakUserCreateUrl, request, String.class);
	}
}
