package com.ff.main.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ff.main.models.Authority;
import com.ff.main.models.Provider;
import com.ff.main.models.Users;
import com.ff.main.repositories.AuthorityRepository;
import com.ff.main.services.ProviderService;
import com.ff.main.services.UsersService;

@RestController
@CrossOrigin
public class MainController {

	@Autowired
	ProviderService ps;
	
	@Autowired
	UsersService us;
	
	@Autowired
	AuthorityRepository repo;
	
	@GetMapping("/register/authority/{email}")
	public Authority getAuthority(@PathVariable String email) {
		return repo.getByEmail(email);
	}
	
	@GetMapping("/providers/{email}")
	public Provider provider(@PathVariable String email) {
		return ps.findByEmail(email);
	}
	
	@GetMapping("/users/{email}")
	public Users user(@PathVariable String email) {
		return us.findByEmail(email);
	}
}
