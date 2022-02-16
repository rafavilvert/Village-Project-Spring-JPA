package com.api.villagedevin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.villagedevin.model.transport.UserDTO;
import com.api.villagedevin.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/list-all")
	public List<UserDTO> listAll() {
		return userService.listAll();
	}
	
//	@GetMapping("/list-users/{name}")
//	public Citizen listCitizensDetails(@PathVariable("name") String name) {
//		return userService.listCitizens(name);
//	}

}
