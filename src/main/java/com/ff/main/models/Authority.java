package com.ff.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authority {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="authority_id")
	private long authorityId;

	@Column(name="email")
	private String email;
	
	@Column(name="authority")
	private String authority;

	public long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(long authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthority() {
		return authority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
