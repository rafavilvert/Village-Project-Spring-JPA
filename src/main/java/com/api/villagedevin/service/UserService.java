package com.api.villagedevin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.villagedevin.model.persistence.User;
import com.api.villagedevin.model.repository.UserRepository;
import com.api.villagedevin.model.transport.UserDTO;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserDTO> listAll() {
		List<UserDTO> users = new ArrayList<>();
		Iterable<User> iterable = this.userRepository.findAll();
		iterable.forEach(all -> users.add(new UserDTO(all)));
		System.out.println(users);
		return users;
	}

	public List<UserDTO> getUsersByEmail(String email) {
		List<UserDTO> userDTO = new ArrayList<>();
		Iterable<User> iterable = this.userRepository.findByEmail(email);

		iterable.forEach(user -> userDTO.add(new UserDTO(user)));

		return userDTO;
	}

	public ResponseEntity<HttpStatus> create(User user) {
		this.userRepository.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
