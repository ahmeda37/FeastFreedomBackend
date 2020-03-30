package com.ff.main.services;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ff.main.models.Hours;
import com.ff.main.repositories.HoursRepository;

@Service("hourService")
public class HoursService {

	@Autowired
	HoursRepository repo;
	
	public void addAllHours(Set<Hours> h) {
		repo.saveAll(h);
	}
	
	public Set<Hours> getAllHours(){
		return new TreeSet<Hours>(repo.findAll());
	}
	public Set<Hours> getByProvider(Long id){
		return new TreeSet<Hours>(repo.findByProviderProviderId(id));
	}
	
	public void deleteAllId(Set<Long> ids) {
		for(long id : ids) {
			repo.deleteById(id);
		}
	}
}
