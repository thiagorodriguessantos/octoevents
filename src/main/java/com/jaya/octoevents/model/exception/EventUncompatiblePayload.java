package com.jaya.octoevents.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
@NoArgsConstructor
public class EventUncompatiblePayload extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 862348893210477000L;

	public EventUncompatiblePayload(String message) {
		super(message);
	}
	
}
