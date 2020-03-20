package com.jaya.octoevents.config;

import javax.inject.Inject;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.jaya.octoevents.filter.SecurityPayloadFilter;

@Configuration
public class WebMvcConfig {

    @Inject
    private Environment env;	
	
	@Bean
	public FilterRegistrationBean<SecurityPayloadFilter> securityPayloadFilter(){
	    FilterRegistrationBean<SecurityPayloadFilter> registrationBean = new FilterRegistrationBean<SecurityPayloadFilter>();
	    SecurityPayloadFilter securityPayloadFilter = new SecurityPayloadFilter();
	    securityPayloadFilter.setSecret(env.getProperty("octoevents.webhook.secret"));
	    registrationBean.setFilter(securityPayloadFilter);
	    registrationBean.addUrlPatterns("/payload/*");
	    return registrationBean;    
	}
    
}
