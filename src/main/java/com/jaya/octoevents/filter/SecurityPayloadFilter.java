package com.jaya.octoevents.filter;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.jaya.octoevents.model.exception.PayloadNoAuthorizedException;
import com.jaya.octoevents.model.util.ExceptionMessage;
import com.jaya.octoevents.model.util.SecurityPayloadUtil;
import com.jaya.octoevents.wrapper.RequestWrapper;

import lombok.Data;

@Data
public class SecurityPayloadFilter implements Filter{

    private String secret;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
		
		String hash = requestWrapper.getHeader("x-hub-signature");
		String payloadBody = requestWrapper.getReader().lines().collect(Collectors.joining());
		String contentSignIn = null;
		try {
			contentSignIn = SecurityPayloadUtil.calculateHMACSHA1(payloadBody,getSecret());
		} catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
			throw new ServletException(e.getMessage());
		}

		if(!contentSignIn.equals(hash))
			throw new PayloadNoAuthorizedException(ExceptionMessage.PAYLOAD_ACCES_DENIED_MESSAGE);
		
		chain.doFilter(requestWrapper, response);
		
	}

}
