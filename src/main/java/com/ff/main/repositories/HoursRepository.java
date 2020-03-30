package com.ff.main.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.main.models.Hours;

public interface HoursRepository extends JpaRepository<Hours,Long>  {
	Set<Hours> findByProviderProviderId(Long id);

}
