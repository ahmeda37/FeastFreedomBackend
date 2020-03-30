package com.ff.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ff.main.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

	Users findByUserEmail(String email);

}
