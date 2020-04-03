package com.ff.main.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ff.main.beans.EditProviderForm;
import com.ff.main.beans.ProviderRegisterForm;
import com.ff.main.models.Hours;
import com.ff.main.models.Items;
import com.ff.main.models.Provider;
import com.ff.main.services.HoursService;
import com.ff.main.services.ItemsService;
import com.ff.main.services.ProviderService;

@RestController
@CrossOrigin
public class ProviderController {

	@Autowired
	ProviderService ps;

	@Autowired
	HoursService hs;

	@Autowired
	ItemsService is;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/providers/current")
	public Provider getCurrentProvider() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(((UserDetails) authentication.getPrincipal()).getUsername());
		return ps.findByEmail((((UserDetails) authentication.getPrincipal()).getUsername()));
	}

	@PostMapping("/register/provider")
	public Provider addProvider(@RequestBody ProviderRegisterForm prf) {
		prf.getProvider().setProviderPassword(passwordEncoder.encode(prf.getProvider().getProviderPassword()));
		ps.saveProvider(prf.getProvider());

		hs.addAllHours(prf.setPidHours());

		is.addAllItems(prf.setPidItems());
		return prf.getProvider();
	}

	@PostMapping("/register/provider/uploadImage/{id}")
	public void uploadImage(@RequestParam MultipartFile imgInput, @PathVariable Long id) throws IOException {
		Provider p = ps.getProvider(id);
		try {
			String end = "."+imgInput.getContentType().substring(imgInput.getContentType().indexOf("/")+1);
			byte[] bytes = imgInput.getBytes();
			//Path path = Paths.get("D:/Abdurahman/Summit/Project3/feastfreedom-server/FeastFreedom/src/main/resources/static/"+p.getProviderId()+end);
			Path path = Paths.get("/home/tomcat/static/" + p.getProviderId() + end);
			System.out.println(path.toString());
			Files.write(path, bytes);
			p.setProviderImg(p.getProviderId()+end);
			ps.updateProvider(p); 
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/providers/image/{id}")
	public byte[] getImage(@PathVariable Long id) {
		Provider p = ps.getProvider(id);
		return Base64.getDecoder().decode(p.getProviderImg());
	}

	@GetMapping("/providers/items/{id}")
	public Set<Items> getItems(@PathVariable Long id) {
		return is.getByProvider(id);
	}

	@GetMapping("/providers/hours/{id}")
	public Set<Hours> getHours(@PathVariable Long id) {
		return hs.getByProvider(id);
	}

	@PostMapping("/providers/edit")
	public void editProvider(@RequestBody EditProviderForm epf) {
		hs.addAllHours(epf.setPidHours());
		is.addAllItems(epf.setPidItems());
	}

	@GetMapping("/providers/deleteItems")
	public void deleteItems(@RequestParam Set<Long> itemId) {
		System.out.println(itemId);
		is.deleteAllId(itemId);
	}
	
	@GetMapping("/providers/deleteHours")
	public void deleteHours(@RequestParam Set<Long> hourId) {
		System.out.println(hourId);
		hs.deleteAllId(hourId);
		//return "done";
	}
}
