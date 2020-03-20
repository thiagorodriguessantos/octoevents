package com.jaya.octoevents.wrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper {
    private final String body;
 
    public RequestWrapper(HttpServletRequest request) throws IOException 
    {
        super(request);
        body = request.getReader().lines().collect(Collectors.joining());
    }
 
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener listener) {
				// TODO Auto-generated method stub
				
			}
        };
        return servletInputStream;
    }
 
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
 
    //Use this method to read the request body N times
    public String getBody() {
        return this.body;
    }
}