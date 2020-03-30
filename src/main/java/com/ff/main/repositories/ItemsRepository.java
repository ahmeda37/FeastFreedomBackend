package com.ff.main.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.main.models.Items;

public interface ItemsRepository extends JpaRepository<Items,Long>{
	Set<Items> findByProviderProviderId(Long id);
}
