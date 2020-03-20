package com.jaya.octoevents.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@NoArgsConstructor
public class PayloadNoAuthorizedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7171174245013863704L;

	public PayloadNoAuthorizedException(String message) {
		super(message);
	}
	
}
