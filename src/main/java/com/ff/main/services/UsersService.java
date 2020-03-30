package com.ff.main.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ff.main.models.Authority;
import com.ff.main.models.Users;
import com.ff.main.repositories.AuthorityRepository;
import com.ff.main.repositories.UsersRepository;

@Service("usersService")
public class UsersService {

	@Autowired
	UsersRepository repo;
	
	@Autowired
	AuthorityRepository authRepo;
	
	public Users getUserById(long id) {
		return repo.findById(id).get();
	}
	
	public Set<Users> getUsers(){
		return new HashSet<Users>(repo.findAll());
	}
	
	public void addUser(Users u) {
		repo.save(u);
		Authority a = new Authority();
		a.setEmail(u.getUserEmail());
		a.setAuthority("ROLE_USER");
		authRepo.save(a);
	}
	
	public void deleteUserById(long id) {
		repo.deleteById(id);
	}

	public Users findByEmail(String email) {
		// TODO Auto-generated method stub
		return repo.findByUserEmail(email);
	}
}
