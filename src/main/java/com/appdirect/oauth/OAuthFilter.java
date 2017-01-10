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
//   private static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
//   private static final String OAUTH_NONCE = "oauth_nonce";
//   private static final String OAUTH_SIGNATURE_METHOD = "oauth_signature_method";
//   private static final String OAUTH_TIMESTAMP = "oauth_timestamp";
//   private static final String OAUTH_VERSION = "oauth_version";
//   private static final String OAUTH_SIGNATURE = "oauth_signature";
//
//   private static final Logger LOGGER = LoggerFactory.getLogger(OAuthFilter.class);
//   
//   private final RequestValidator requestValidator;
//   
//   @Autowired
//   public OAuthFilter(RequestValidator requestValidator) {
//      this.requestValidator = requestValidator;
//   }
//   
//   @Override
//   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//      final String oauthSignature;
//      final String oauthConsumerKey;
//      final String oauthNonce;
//      final String oauthSignatureMethod;
//      final String oauthTimestamp;
//      final String oauthVersion;
//      String authorizationHeader = ((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION);
//      if(!Strings.isNullOrEmpty(authorizationHeader)) {
//         Map<String, String> oauthParameters = Maps.newHashMap();
//         try {
//            oauthParameters = Splitter.on(",").withKeyValueSeparator("=").split(authorizationHeader);
//         } catch(IllegalArgumentException e) {
//            LOGGER.warn("Authorization header {} is malformed.", authorizationHeader);
//         }
//         oauthSignature = oauthParameters.get(OAUTH_SIGNATURE);
//         oauthConsumerKey = oauthParameters.get(OAUTH_CONSUMER_KEY);
//         oauthNonce = oauthParameters.get(OAUTH_NONCE);
//         oauthSignatureMethod = oauthParameters.get(OAUTH_SIGNATURE_METHOD);
//         oauthTimestamp = oauthParameters.get(OAUTH_TIMESTAMP);
//         oauthVersion = oauthParameters.get(OAUTH_VERSION);
//      } else {
//         // If header is empty, check URL parameters
//         oauthSignature = request.getParameter(OAUTH_SIGNATURE);
//         oauthConsumerKey = request.getParameter(OAUTH_CONSUMER_KEY);
//         oauthNonce = request.getParameter(OAUTH_NONCE);
//         oauthSignatureMethod = request.getParameter(OAUTH_SIGNATURE_METHOD);
//         oauthTimestamp = request.getParameter(OAUTH_TIMESTAMP);
//         oauthVersion = request.getParameter(OAUTH_VERSION);
//      }
//      requestValidator.validateOauthParameters(oauthConsumerKey, oauthNonce, oauthSignature, oauthSignatureMethod, oauthTimestamp, oauthVersion);
//   }

}
