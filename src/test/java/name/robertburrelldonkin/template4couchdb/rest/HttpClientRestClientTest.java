/*
* 	Copyright 2012 Robert Burrell Donkin robertburrelldonkin.name
* 
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/
package name.robertburrelldonkin.template4couchdb.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import name.robertburrelldonkin.template4couchdb.IDocumentMarshaller;
import name.robertburrelldonkin.template4couchdb.IDocumentUnmarshaller;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HttpClientRestClientTest {
	
	private static final String PROTOCOL = "http";
	private static final int PORT = 2342;
	private static final String PATH = "/some/path";
	private static final String HOST = "example.org";
	private static final String URL = PROTOCOL + "://" +  HOST + ":" + PORT + PATH;
	

	String unmarshalledResponse = "{message: 'whatever'}";
	
	String document = "{hello:'world'}";
	
	
	@Mock
	OutputStream stream;
	
	@Mock
	IDocumentMarshaller<String> documentMarshaller;
	
	@Mock
	IDocumentUnmarshaller<String> responseUnmarshaller;
	
	@Mock
	ContentProducer producer;
	
	@Mock
	ResponseHandler<String> handler;
	
	@Mock
	IDocumentUnmarshaller<String> mapper;
	
	@Mock
	ICodecFactory codecFactory;
	
	@Mock
	HttpClient client;
	
	@Mock
	ClientConnectionManager connectionManager;
	
	HttpClientRestClient subject;
	
	@Before
	public void before() throws Exception {
		when(handler.handleResponse((HttpResponse) anyObject())).thenReturn(unmarshalledResponse);
		when(codecFactory.handlerFor(mapper)).thenReturn(handler);
		when(codecFactory.handlerFor(responseUnmarshaller)).thenReturn(handler);
		when(codecFactory.producerFor(documentMarshaller, document)).thenReturn(producer);

		this.subject = new HttpClientRestClient(client, codecFactory);
	}
	
	@Test
	public void testShutdownShutsDownClient() {
		when(client.getConnectionManager()).thenReturn(connectionManager);
		this.subject.shutdown();
		verify(this.connectionManager).shutdown();
	}
	
	@Test
	public void smokeShutdownWhenManagerIsNull() {
		when(client.getConnectionManager()).thenReturn(null);
		this.subject.shutdown();
	}
	
	@Test
	public void testGetUsesResponseHandler() {
		subject.get(URL, mapper);
		verify(codecFactory).handlerFor(mapper);
	}

	@Test
	public void testGetCallsHttpClient() throws Exception {
		subject.get(URL, mapper);
		verify(client).execute((HttpGet) anyObject(), eq(handler));
	}
	
	@Test
	public void testGetUrl() throws Exception {
		subject.get(URL, mapper);
		ArgumentCaptor<HttpGet> argument = ArgumentCaptor.forClass(HttpGet.class);
		verify(client).execute(argument.capture(), eq(handler));
		URI uri = argument.getValue().getURI();
		assertThat(PORT, is(uri.getPort()));
		assertThat(HOST, is(uri.getHost()));
		assertThat(PROTOCOL, is(uri.getScheme()));
		assertThat(PATH, is(uri.getPath()));
	}
	
	@Test(expected = HttpClientRestClientException.class)
	public void testGetClientProcotolExceptionRethrow() throws Exception {
		when(client.execute((HttpGet) anyObject(), eq(handler))).thenThrow(new ClientProtocolException());
		subject.get(URL, mapper);
	}
	
	@Test(expected = HttpClientRestClientException.class)
	public void testGetIOExceptionRethrow() throws Exception {
		when(client.execute((HttpGet) anyObject(), eq(handler))).thenThrow(new IOException());
		subject.get(URL, mapper);
	}
	
	@Test
	public void testPostBuildsContentProducer() {
		this.subject.post(URL, documentMarshaller, document, responseUnmarshaller);
		verify(this.codecFactory).producerFor(documentMarshaller, document);
	}

	@Test
	public void testPostBuildsResponseHandler() {
		this.subject.post(URL, documentMarshaller, document, responseUnmarshaller);
		verify(this.codecFactory).handlerFor(responseUnmarshaller);
	}
	
	@Test
	public void testPostUrl() throws Exception {
		ArgumentCaptor<HttpPost> argument = ArgumentCaptor.forClass(HttpPost.class);
		this.subject.post(URL, documentMarshaller, document, responseUnmarshaller);
		verify(client).execute(argument.capture(), eq(handler));
		URI uri = argument.getValue().getURI();
		assertThat(PORT, is(uri.getPort()));
		assertThat(HOST, is(uri.getHost()));
		assertThat(PROTOCOL, is(uri.getScheme()));
		assertThat(PATH, is(uri.getPath()));
	}

	@Test(expected = HttpClientRestClientException.class)
	public void testPostClientProcotolExceptionRethrow() throws Exception {
		when(client.execute((HttpPost) anyObject(), eq(handler))).thenThrow(new ClientProtocolException());
		this.subject.post(URL, documentMarshaller, document, responseUnmarshaller);
	}
	
	@Test(expected = HttpClientRestClientException.class)
	public void testPostIOExceptionRethrow() throws Exception {
		when(client.execute((HttpPost) anyObject(), eq(handler))).thenThrow(new IOException());
		this.subject.post(URL, documentMarshaller, document, responseUnmarshaller);
	}

	@Test
	public void testPostResponse() throws Exception {
		when(client.execute((HttpGet) anyObject(), eq(handler))).thenReturn(unmarshalledResponse);
		assertThat(this.subject.post(URL, documentMarshaller, document, responseUnmarshaller), 
				is(unmarshalledResponse));
	}
	
	@Test
	public void testPostContent() throws Exception {
		ArgumentCaptor<HttpPost> argument = ArgumentCaptor.forClass(HttpPost.class);
		this.subject.post(URL, documentMarshaller, document, responseUnmarshaller);
		verify(client).execute(argument.capture(), eq(handler));
		EntityTemplate entity = (EntityTemplate) argument.getValue().getEntity() ;
		assertNotNull(entity);
		entity.writeTo(stream);
		verify(producer).writeTo(stream);
	}

}
