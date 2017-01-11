package com.appdirect.oauth;

import org.springframework.security.oauth.provider.OAuthProcessingFilterEntryPoint;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.nonce.OAuthNonceServices;
import org.springframework.stereotype.Component;

@Component
public class OAuthFilter extends ProtectedResourceProcessingFilter {

   public OAuthFilter(OAuthConsumerDetailsService oAuthConsumerDetailsService,
         OAuthNonceServices oAuthNonceServices,
         OAuthProcessingFilterEntryPoint oAuthProcessingFilterEntryPoint) {
      super();
      setAuthenticationEntryPoint(oAuthProcessingFilterEntryPoint);
      setConsumerDetailsService(oAuthConsumerDetailsService);
      setNonceServices(oAuthNonceServices);
   }
}
