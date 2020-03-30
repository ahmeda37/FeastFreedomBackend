package com.ff.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ff.main.models.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{
	Provider findByProviderEmail(String email);
}
