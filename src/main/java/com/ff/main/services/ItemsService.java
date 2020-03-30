package com.ff.main.services;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ff.main.models.Items;
import com.ff.main.repositories.ItemsRepository;

@Service("itemService")
public class ItemsService {

	
	@Autowired
	ItemsRepository repo;
	
	public void addAllItems(Set<Items> h) {
		repo.saveAll(h);
	}
	
	public Set<Items> getAllItems(){
		return new TreeSet<Items>(repo.findAll());
	}
	
	public Set<Items> getByProvider(Long id){
		return new TreeSet<Items>(repo.findByProviderProviderId(id));
	}
	
	public void deleteAllId(Set<Long> ids) {
		for(long id : ids) {
			repo.deleteById(id);
		}
	}
}
