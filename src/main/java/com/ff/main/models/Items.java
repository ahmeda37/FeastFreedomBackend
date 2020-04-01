package com.ff.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="items")
public class Items implements Comparable<Items> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_id")
	private long itemId;
	
	@Column(name="item_name")
	@NotNull
	private String itemName;
	
	@Column(name="vegetarian")
	@NotNull
	private boolean vegetarian;
	
	@Column(name="item_price")
	@NotNull
	private float itemPrice;
	
	@OneToOne()
	@JoinColumn(name="provider_id", referencedColumnName="provider_id")
	@NotNull
	private Provider provider;
	

	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public boolean isVegetarian() {
		return vegetarian;
	}
	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}
	public float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public int compareTo(Items o) {
		// TODO Auto-generated method stub
		if(this.itemId > o.itemId) {return 100;}
		else if(this.itemId < o.itemId) {return -100;}
		return 0;
	}
	
	@Override
	public String toString() {
		String result = "";
		result += this.itemName + " Veg: " + this.vegetarian + " $" + this.getItemPrice();
		return result;
	}
	
}
