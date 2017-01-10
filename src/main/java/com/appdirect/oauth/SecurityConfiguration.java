package com.appdirect.oauth;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth.provider.OAuthProcessingFilterEntryPoint;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.nonce.InMemoryNonceServices;
import org.springframework.security.oauth.provider.nonce.OAuthNonceServices;
import org.springframework.security.oauth.provider.token.InMemoryProviderTokenServices;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
 @Order(1)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   private ProtectedResourceProcessingFilter oauthFilter;

   private OAuthConsumerDetailsService oauthConsumerDetailsService;

   @Autowired
   public SecurityConfiguration(OAuthConsumerDetailsService oauthConsumerDetailsService 
         ) {
      this.oauthConsumerDetailsService = oauthConsumerDetailsService;
   }
   @PostConstruct
   public void init() {
      oauthFilter = new OAuthFilter(oauthConsumerDetailsService, oAuthNonceServices(), oauthAuthenticationEntryPoint());
   }

   @Bean
   public OAuthNonceServices oAuthNonceServices() {
      return new InMemoryNonceServices();
   }
   
   @Bean
   public OAuthProcessingFilterEntryPoint oauthAuthenticationEntryPoint() {
       return new OAuthProcessingFilterEntryPoint();
   }
   @Bean
   public OAuthProviderTokenServices oauthProviderTokenServices() {
      return new InMemoryProviderTokenServices();
   }
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.antMatcher("/**").addFilterBefore(oauthFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests().anyRequest().hasRole("OAUTH");
   }
}
