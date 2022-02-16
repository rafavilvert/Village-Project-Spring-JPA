package com.api.villagedevin.service;

import java.util.ArrayList;
import java.util.List;

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

}
