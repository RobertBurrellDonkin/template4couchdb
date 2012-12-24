package name.robertburrelldonkin.template4couchdb.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

import name.robertburrelldonkin.template4couchdb.IDocumentUnmarshaller;
import name.robertburrelldonkin.template4couchdb.rest.DefaultResponseHandler;
import name.robertburrelldonkin.template4couchdb.rest.UnsupportedHttpResponseStatusException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultResponseHandlerTest {

	public final String REASON_PHRASE = "A reason";
	
	public final String OUTPUT = "{}";
	
	@Mock
	StatusLine statusLine;
	
	@Mock
	InputStream inputStream;
	
	@Mock
	HttpEntity entity;
	
	@Mock
	HttpResponse response;
	
	@Mock
	IDocumentUnmarshaller<String> mapper;
	
	DefaultResponseHandler<String> subject;
	
	@Before
	public void before() throws Exception {
		when(response.getStatusLine()).thenReturn(statusLine);
		when(statusLine.getStatusCode()).thenReturn(HttpStatus.SC_OK);
		when(statusLine.getReasonPhrase()).thenReturn(REASON_PHRASE);
		when(response.getEntity()).thenReturn(entity);
		when(entity.getContent()).thenReturn(inputStream);
		when(mapper.from(inputStream)).thenReturn(OUTPUT);
		
		this.subject = new DefaultResponseHandler<String>(mapper);
	}
	
	@Test
	public void testHandleResponseInputsResponseToMapper() throws Exception {
		this.subject.handleResponse(response);
		verify(mapper).from(inputStream);
	}
	
	@Test
	public void testHandleResponseResult() throws Exception {
		assertThat(this.subject.handleResponse(response), is(OUTPUT));
	}

	@Test
	public void testHandleResponseClosesStream() throws Exception {
		this.subject.handleResponse(response);
		verify(inputStream).close();
	}
	
	@Test
	public void testHandleResponseClosesStreamAfterException() throws Exception {
		when(this.mapper.from(inputStream)).thenThrow(new IllegalArgumentException());
		try {
			this.subject.handleResponse(response);
		} catch (IllegalArgumentException e){
			// expected
		}
		verify(inputStream).close();
	}
	
	@Test
	public void whenStatusCodeIsNotOkThenHandleResponseShouldThrowException() 
			throws ClientProtocolException, IOException {
		for (int i=1;i<600;i++) {
			if (i != HttpStatus.SC_OK) {
				try {
					when(statusLine.getStatusCode()).thenReturn(i);
					this.subject.handleResponse(response);
					fail("Status Code " + i 
							+ " expected to throw UnsupportedHttpResponseStatusException");
				} catch (UnsupportedHttpResponseStatusException e) {
					assertThat(e.getStatusCode(), is(i));
					assertThat(e.getReasonPhrase(), is(REASON_PHRASE));
				}
			}
		}
	}
}
