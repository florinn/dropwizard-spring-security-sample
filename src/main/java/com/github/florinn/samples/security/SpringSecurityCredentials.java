package com.github.florinn.samples.security;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;

public class SpringSecurityCredentials {

	private String username;
	private String password;
	
	public SpringSecurityCredentials(String username, String password) {
		this.username = checkNotNull(username);
		this.password = checkNotNull(password);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final SpringSecurityCredentials other = (SpringSecurityCredentials) obj;

		return Objects.equal(this.getUsername(), other.getUsername()) && 
				Objects.equal(this.getPassword(), other.getPassword());
	}
	
	@Override
	public int hashCode() {
		 return Objects.hashCode(
				 this.getUsername(), this.getPassword());
	}

	@Override
	public String toString() {
		 return Objects.toStringHelper(this)
				 .add("username", this.getPassword())
				 .toString();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
