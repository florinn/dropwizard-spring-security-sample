package com.github.florinn.samples.security;

import java.io.Serializable;

public class Principal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7103315600939610068L;
	
	private String username;
		
	public Principal() {
	}
	public Principal(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return username;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}