package com.appdirect.oauth;

import java.net.HttpURLConnection;

@FunctionalInterface
public interface RequestSigner {
   HttpURLConnection getSignedConnection(final String url);
}
