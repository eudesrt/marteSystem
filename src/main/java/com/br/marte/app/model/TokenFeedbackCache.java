package com.br.marte.app.model;

import java.util.HashMap;
import java.util.Map;

public class TokenFeedbackCache {
	private static Map<String, TokenFeedback> cacheToken = new HashMap<>();
	
	
	public static TokenFeedback getTokenFeedback(String login) {
		return cacheToken.get(login);
	}
	
	public static TokenFeedback addTokenFeedback(TokenFeedback tokenFeedback) {
		return cacheToken.put(tokenFeedback.getLogin(), tokenFeedback);
	}
}
