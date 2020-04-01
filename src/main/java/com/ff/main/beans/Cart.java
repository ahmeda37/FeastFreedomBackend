package com.ff.main.beans;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.ff.main.models.Items;
import com.ff.main.models.Provider;
import com.ff.main.models.Users;

public class Cart {
	private Provider provider;
	private Users user;
	private Map<String,Items> items;
	
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Map<String, Items> getItems() {
		return items;
	}
	public void setItems(Map<String, Items> items) {
		this.items = items;
	}
	
	
	@Override
	public String toString() {
		String result="";
		result += provider.getProviderName() + "\n";
		result += user.getUserFirstName() + " " + user.getUserLastName() + "\n";
				
		Iterator<Entry<String, Items>> it = items.entrySet().iterator();
		
		while(it.hasNext()) {
			Entry<String,Items> e = it.next();
			result += e.getValue().toString() + "\n";
		}
		return result;
	}
}
