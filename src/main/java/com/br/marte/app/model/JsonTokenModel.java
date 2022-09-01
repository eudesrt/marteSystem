package com.br.marte.app.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonTokenModel {
	public String access_token;
	public String token_type;
	public Integer expires_in;
	public String scope;
	public Integer limitOS_usuario;
	public Integer usuario_id;
	public String nome;
	public String jti;
	
	
	public static JsonTokenModel create(String json) {
		try {
			return new Gson().fromJson(json, JsonTokenModel.class);
		} catch (Exception e) {
			return null;
		}

	}

	public String toJSON() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Integer getLimitOS_usuario() {
		return limitOS_usuario;
	}

	public void setLimitOS_usuario(Integer limitOS_usuario) {
		this.limitOS_usuario = limitOS_usuario;
	}

	public Integer getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Integer usuario_id) {
		this.usuario_id = usuario_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}
	
	
}
