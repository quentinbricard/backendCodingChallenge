package com.appdirect;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.config.BeanIds;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.FrameworkServlet;

import com.appdirect.oauth.OAuthFilter;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BackendCodingChallengeApplication.class);
	}
//	@Override 
//   public void onStartup(ServletContext servletContext) throws ServletException { 
//      super.onStartup(servletContext); 
//      DelegatingFilterProxy filter = new DelegatingFilterProxy(BeanIds.SPRING_SECURITY_FILTER_CHAIN); 
//      filter.setContextAttribute(FrameworkServlet.SERVLET_CONTEXT_PREFIX + "dispatcher");
//      servletContext.addFilter(BeanIds.SPRING_SECURITY_FILTER_CHAIN, filter).addMappingForUrlPatterns(null, false, "/*"); 
//      servletContext.addFilter(BeanIds.SPRING_SECURITY_FILTER_CHAIN, OAuthFilter.class).addMappingForUrlPatterns(null, false, "/*"); 
//   }  
}
