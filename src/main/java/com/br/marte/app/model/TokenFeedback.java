package com.br.marte.app.model;

public class TokenFeedback {
	private String token;
	private String login;
	
	

	public TokenFeedback(String token, String login) {
		this.token = token;
		this.login = login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
