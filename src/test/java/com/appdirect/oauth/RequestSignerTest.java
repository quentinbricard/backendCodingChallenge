package com.appdirect.oauth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.appdirect.oauth.exception.OAuthException;
import com.appdirect.oauth.impl.RequestSignerImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest({URL.class, RequestSignerImpl.class})
public class RequestSignerTest {

   private static final String URL = "URL";
   private RequestSigner requestSigner;
   private URL urlMock;
   
   @Before
   public void setUp() throws Exception {
      urlMock = PowerMockito.mock(URL.class);
      PowerMockito.whenNew(URL.class).withArguments(URL).thenReturn(urlMock);
      requestSigner = new RequestSignerImpl();
   }
   
   @SuppressWarnings("unchecked")
   @Test
   public void wrongURLtest() throws Exception {
      PowerMockito.whenNew(URL.class).withArguments(URL).thenThrow(MalformedURLException.class);
      try {
         requestSigner.getSignedConnection(URL);
         fail("An exception should have occurred");
      } catch(OAuthException e) {
         assertNotNull(e.getCause());
         assertEquals(MalformedURLException.class, e.getCause().getClass());
      }
   }
   
   @SuppressWarnings("unchecked")
   @Test
   public void errorOpeningConnectiontest() throws IOException {
      Mockito.when(urlMock.openConnection()).thenThrow(IOException.class);
      try {
         requestSigner.getSignedConnection(URL);
         fail("An exception should have occurred");
      } catch(OAuthException e) {
         assertNotNull(e.getCause());
         assertEquals(IOException.class, e.getCause().getClass());
      }
   }
}
