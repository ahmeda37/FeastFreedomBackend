package com.ff.main.services;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ff.main.models.Provider;
import com.ff.main.models.Authority;
import com.ff.main.repositories.AuthorityRepository;
import com.ff.main.repositories.ProviderRepository;

@Service("providerService")
public class ProviderService {

	@Autowired
	ProviderRepository repo;
	
	@Autowired
	AuthorityRepository authRepo;
	
	public Provider getProvider(long id) {
		return repo.findById(id).get();
	}
	
	public Provider findByEmail(String email) {
		return repo.findByProviderEmail(email);
	}
	public Set<Provider> getProviders(){
		return new TreeSet<Provider>(repo.findAll());
	}
	
	public void saveProvider(Provider p) {
		repo.save(p);
		Authority pa = new Authority();
		pa.setEmail(p.getProviderEmail());
		pa.setAuthority("ROLE_PROVIDER");
		authRepo.save(pa);
	}

	public void updateProvider(Provider p) {
		repo.save(p);
	}
	
	public void deleteProvider(long id) {
		repo.deleteById(id);
	}
}
