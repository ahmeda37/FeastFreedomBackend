package com.ff.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="providers", indexes = {@Index(columnList=("provider_email"), unique=true)})
public class Provider implements Comparable<Provider>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="provider_id")
	private long providerId;
	
	@Column(name="provider_name")
	private String providerName;
	
	@Column(name="provider_email")
	@NotNull
	private String providerEmail;
	
	@Column(name="provider_password")
	@NotNull
	private String providerPassword;
	
	@Lob
	@Column(name="provider_img")
	private String providerImg;
	
	
	
	public String getProviderImg() {
		return providerImg;
	}

	public void setProviderImg(String providerImg) {
		this.providerImg = providerImg;
	}

	public long getProviderId() {
		return providerId;
	}

	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderEmail() {
		return providerEmail;
	}

	public void setProviderEmail(String providerEmail) {
		this.providerEmail = providerEmail;
	}

	public String getProviderPassword() {
		return providerPassword;
	}

	public void setProviderPassword(String providerPassword) {
		this.providerPassword = providerPassword;
	}

	@Override
	public int compareTo(Provider o) {
		if(this.providerId > o.providerId) {return 100;}
		else if(this.providerId < o.providerId) {return -100;}
		return 0;
	}
	
}
