package com.ff.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.main.models.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority getByEmail(String email);
}
