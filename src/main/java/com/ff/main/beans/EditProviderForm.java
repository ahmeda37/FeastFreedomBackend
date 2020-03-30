package com.ff.main.beans;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.ff.main.models.Hours;
import com.ff.main.models.Items;
import com.ff.main.models.Provider;

public class EditProviderForm {
	private Provider provider;
	private Map<String, Hours> hours;
	private Map<String, Items> items;

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public Map<String, Items> getItems() {
		return items;
	}

	public void setItems(Map<String, Items> items) {
		this.items = items;
	}

	public Map<String, Hours> getHours() {
		return hours;
	}

	public void setHours(Map<String, Hours> hours) {
		this.hours = hours;
	}

	public Set<Hours> setPidHours() {
		Set<Hours> h = new HashSet<Hours>();

		Iterator<Entry<String, Hours>> i = hours.entrySet().iterator();

		while (i.hasNext()) {
			Entry<String, Hours> e = i.next();

			if (e.getValue().isOpen()) {
				e.getValue().setProvider(provider);
				h.add(e.getValue());
			}
		}
		return h;
	}

	public Set<Items> setPidItems() {
		Set<Items> h = new HashSet<Items>();

		Iterator<Entry<String, Items>> i = items.entrySet().iterator();

		while (i.hasNext()) {
			Entry<String, Items> e = i.next();
			e.getValue().setProvider(provider);
			h.add(e.getValue());
		}
		return h;
	}
}