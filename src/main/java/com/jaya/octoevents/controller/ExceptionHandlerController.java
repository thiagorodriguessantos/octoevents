package com.jaya.octoevents.controller;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jaya.octoevents.model.EndPointError;
import com.jaya.octoevents.model.exception.EventNotFoundException;
import com.jaya.octoevents.model.exception.EventUncompatiblePayload;
import com.jaya.octoevents.model.exception.PayloadNoAuthorizedException;
import com.jaya.octoevents.model.service.EndPointErrorService;
import com.jaya.octoevents.model.util.ExceptionMessage;

@RestController
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
	
	@Inject
	private EndPointErrorService errorService;
	
	
	@ExceptionHandler(Exception.class) 
	public final ResponseEntity<EndPointError> handleAllExceptions(Exception ex, WebRequest request) {
		
		EndPointError error = new EndPointError(ex.getMessage(), request.getDescription(true), request.getDescription(false), new Date());
		errorService.persistError(error);
		return new ResponseEntity<EndPointError>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(EventNotFoundException.class) 
	public final ResponseEntity<EndPointError> handleEventNotFoundException(Exception ex, WebRequest request) {
		EndPointError error = new EndPointError(ExceptionMessage.EVENT_NOT_FOUND_MESSAGE, request.getDescription(true), request.getDescription(false), new Date());
		return new ResponseEntity<EndPointError>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EventUncompatiblePayload.class) 
	public final ResponseEntity<EndPointError> handleEventUncompatiblePayload(Exception ex, WebRequest request) {
		EndPointError error = new EndPointError(ExceptionMessage.EVENT_UNCOMPATIBLE_PAYLOAD_MESSAGE, request.getDescription(true), request.getDescription(false), new Date());
		return new ResponseEntity<EndPointError>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PayloadNoAuthorizedException.class) 
	public final ResponseEntity<EndPointError> handleEventPayloadNoAuthorized(Exception ex, WebRequest request) {
		EndPointError error = new EndPointError(ExceptionMessage.PAYLOAD_ACCES_DENIED_MESSAGE, request.getDescription(true), request.getDescription(false), new Date());
		return new ResponseEntity<EndPointError>(error, HttpStatus.UNAUTHORIZED);		
	}
	
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		EndPointError error = new EndPointError(ex.getMessage(), request.getDescription(true), request.getDescription(false), new Date());
		errorService.persistError(error);
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
		
	} 
	
	@Override
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		EndPointError error = new EndPointError(ex.getMessage(), request.getDescription(true), request.getDescription(false), new Date());
		errorService.persistError(error);
		return new ResponseEntity<Object>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	 

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		EndPointError error = new EndPointError(ex.getMessage(), request.getDescription(true), request.getDescription(false), new Date());
		errorService.persistError(error);
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	
}