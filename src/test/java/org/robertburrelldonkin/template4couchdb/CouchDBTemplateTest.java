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
package org.robertburrelldonkin.template4couchdb;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.robertburrelldonkin.template4couchdb.CouchDBTemplate;
import org.robertburrelldonkin.template4couchdb.CouchDatabase;
import org.robertburrelldonkin.template4couchdb.CouchDatabaseBuilder;
import org.robertburrelldonkin.template4couchdb.IDocumentMarshaller;
import org.robertburrelldonkin.template4couchdb.IDocumentUnmarshaller;
import org.robertburrelldonkin.template4couchdb.IRestClient;

@RunWith(MockitoJUnitRunner.class)
public class CouchDBTemplateTest {
	
	CouchDatabase couchDatabase;
	
	@Mock
	IRestClient client;
	
	@Mock
	IDocumentMarshaller<String> documentMarshaller;
	
	@Mock
	IDocumentUnmarshaller<String> couchResponseUnmarshaller;
	
	@Mock
	IDocumentUnmarshaller<String> documentUnmarshaller;
	
	CouchDBTemplate<String> subject;
	
	@Before
	public void before() {
		couchDatabase = CouchDatabaseBuilder.aCouchDatabase().build();
		this.subject = new CouchDBTemplate<String>(client, couchDatabase, couchResponseUnmarshaller);
	}
	
	@Test
	public void testShutdownShutsDownClient() {
		this.subject.shutdown();
		verify(this.client).shutdown();
	}
	
	@Test
	public void testDelegateGetToRestClient() {
		String id = "doc/23234";
		String result = "{some: 'stuff'}";
		when(this.client.get((String)anyObject(), eq(documentUnmarshaller))).thenReturn(result);
		assertThat(this.subject.get(id, documentUnmarshaller), is(result));
		verify(this.client).get(couchDatabase.urlFor(id), documentUnmarshaller);
	}
	
	@Test
	public void testDelegateVersionToRestClient() {
		String result = "{some: 'stuff'}";
		when(this.client.get((String)anyObject(), eq(documentUnmarshaller))).thenReturn(result);
		assertThat(this.subject.get(couchDatabase.getCouchUrl(), documentUnmarshaller), is(result));
	}
	
	@Test
	public void testDelegatePostToRestClient() {
		String result = "{some: 'stuff'}";
		String document = "{number: '6'}";
		when(this.client.post(
				(String)anyObject(), 
				eq(documentMarshaller), 
				eq(document), 
				eq(couchResponseUnmarshaller))).thenReturn(result);
		String intoDirectory = "some/path";
		this.subject.post(intoDirectory, documentMarshaller, document);
		verify(this.client).post(couchDatabase.urlFor(intoDirectory), documentMarshaller, document, couchResponseUnmarshaller);
	}

	
	@Test
	public void testPostResult() {
		String result = "{more: 'stuff'}";
		String document = "{doc: 'ument'}";
		when(this.client.post(
				(String)anyObject(), 
				eq(documentMarshaller), 
				eq(document), 
				eq(couchResponseUnmarshaller))).thenReturn(result);
		String intoDirectory = "some/path";
		assertThat(this.subject.post(intoDirectory, documentMarshaller, document), is(result));
	}
	
	
	@Test
	public void testConveniencePost() {
		String result = "{yet: 'more stuff'}";
		String document = "{high: '5'}";
		when(this.client.post(
				(String)anyObject(), 
				eq(documentMarshaller), 
				eq(document), 
				eq(couchResponseUnmarshaller))).thenReturn(result);
		this.subject.post(documentMarshaller, document);
		verify(this.client).post(couchDatabase.getDataBaseUrl(), documentMarshaller, document, couchResponseUnmarshaller);
	}

}
