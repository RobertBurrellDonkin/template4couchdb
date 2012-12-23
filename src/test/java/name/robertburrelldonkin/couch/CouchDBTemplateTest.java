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
package name.robertburrelldonkin.couch;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CouchDBTemplateTest {
	
	CouchDatabase couchDatabase;
	
	@Mock
	IRestClient client;
	
	@Mock
	IDocumentMapper<String> mapper;
	
	CouchDBTemplate subject;
	
	@Before
	public void before() {
		couchDatabase = CouchDatabaseBuilder.aCouchDatabase().build();
		this.subject = new CouchDBTemplate(client, couchDatabase);
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
		when(this.client.get((String)anyObject(), eq(mapper))).thenReturn(result);
		assertThat(this.subject.get(id, mapper), is(result));
		verify(this.client).get(couchDatabase.urlFor(id), mapper);
	}
}
