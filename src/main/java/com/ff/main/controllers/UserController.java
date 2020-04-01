package com.ff.main.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ff.main.beans.Cart;
import com.ff.main.models.Hours;
import com.ff.main.models.Items;
import com.ff.main.models.Provider;
import com.ff.main.models.Users;
import com.ff.main.services.HoursService;
import com.ff.main.services.ItemsService;
import com.ff.main.services.ProviderService;
import com.ff.main.services.UsersService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	UsersService us;
	
	@Autowired
	ProviderService ps;
	
	@Autowired
	ItemsService is;
	
	@Autowired
	HoursService hs;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@PostMapping("/register/users")
	public Users addUser(@RequestBody Users u) {
		u.setUserPassword(passwordEncoder.encode(u.getUserPassword()));
		us.addUser(u);
		return u;
	}
	
	@GetMapping("/users/providers")
	public Set<Provider> getAllProviders(){
		return ps.getProviders();
	}
	
	@GetMapping("/users/providers/{id}")
	public Provider getProvider(@PathVariable Long id) {
		return ps.getProvider(id);
	}
	
	@GetMapping("/users/providers/items/{id}")
	public Set<Items> getItems(@PathVariable Long id){
		return is.getByProvider(id);
	}
	
	@GetMapping("/users/providers/hours/{id}")
	public Set<Hours> getHours(@PathVariable Long id){
		return hs.getByProvider(id);
	}
	
	
	@PostMapping("/users/cart")
	public void submitCart(@RequestBody Cart c) {
		c.setProvider(c.getItems().get("0").getProvider());
		sendEmail(c);
	}
	
	public void sendEmail(Cart c) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(c.getProvider().getProviderEmail(), c.getUser().getUserEmail());
		msg.setFrom("ahmeda90210@gmail.com");
		msg.setSubject("Order from "+c.getProvider().getProviderName());
		msg.setText(c.toString());
		
		javaMailSender.send(msg);
	}
}
