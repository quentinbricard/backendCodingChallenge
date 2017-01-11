package com.appdirect.subscription.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNull;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;

import com.appdirect.oauth.RequestSigner;
import com.appdirect.oauth.impl.RequestSignerImpl;
import com.appdirect.subscription.exception.SubscriptionException;
import com.appdirect.subscription.service.impl.RequestHandlerImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileCopyUtils.class)
public class RequestHandlerTest {

   private static final String ACTION = "action";

   private RequestHandler requestHandler;

   private RequestSigner requestSignerMock;
   private HttpURLConnection connectionMock;
   
   @Before
   public void setUp() throws IOException {
      requestSignerMock = mock(RequestSignerImpl.class);
      connectionMock = mock(HttpURLConnection.class);
      when(connectionMock.getResponseCode()).thenReturn(HttpStatus.OK.value());
      when(requestSignerMock.getSignedConnection(Mockito.anyString())).thenReturn(connectionMock);
      PowerMockito.mockStatic(FileCopyUtils.class);
      requestHandler = new RequestHandlerImpl(requestSignerMock);
   }
   
   @SuppressWarnings("unchecked")
   @Test
   public void IOExceptiontest() throws IOException {
      when(connectionMock.getResponseCode()).thenThrow(IOException.class);
      try {
         requestHandler.executeRequest(ACTION, "url");
      } catch(SubscriptionException e) {
         assertEquals(ACTION, e.getAction());
         assertNotNull(e.getCause());
         assertEquals(IOException.class, e.getCause().getClass());
      }
   }
   
   @Test
   public void notOktest() throws IOException {
      when(connectionMock.getResponseCode()).thenReturn(HttpStatus.BAD_REQUEST.value());
      String response = requestHandler.executeRequest(ACTION, "url");
      assertNull(response);
   }
   
   @Test
   public void oktest() {
      String response = requestHandler.executeRequest(ACTION, "url");
      assertEquals("", response);
   }
}
