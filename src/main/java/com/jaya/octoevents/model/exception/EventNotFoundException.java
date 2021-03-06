package com.jaya.octoevents.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class EventNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3020235298897371952L;

	public EventNotFoundException(String message) {
		super(message);
	}
}