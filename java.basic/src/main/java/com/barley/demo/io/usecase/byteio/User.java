package com.barley.demo.io.usecase.byteio;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	
	@Override
	public String toString() {
		return getUsername()+":"+getPassword();
	}
}
