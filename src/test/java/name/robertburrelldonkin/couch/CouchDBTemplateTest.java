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

import static org.mockito.Mockito.*;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CouchDBTemplateTest {
	
	CouchDatabase couchDatabase;
	
	@Mock
	HttpClient client;
	
	@Mock
	ClientConnectionManager connectionManager;
	
	CouchDBTemplate subject;
	
	@Before
	public void before() {
		couchDatabase = CouchDatabaseBuilder.aCouchDatabase().build();
		this.subject = new CouchDBTemplate(client, couchDatabase);
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

}
