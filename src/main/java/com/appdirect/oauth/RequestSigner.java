package com.appdirect.oauth;

import java.net.HttpURLConnection;

@FunctionalInterface
public interface RequestSigner {
   /**
    * <p>Initiates a new connection with the given url.</p>
    * <p>The connection is signed with a key and a secret retrieved from properties.</p>
    * @param url the URL to build
    * @return the {@link HttpURLConnection} built
    */
   HttpURLConnection getSignedConnection(final String url);
}
