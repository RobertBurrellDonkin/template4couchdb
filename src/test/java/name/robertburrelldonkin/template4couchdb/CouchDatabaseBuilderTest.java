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
package name.robertburrelldonkin.template4couchdb;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import name.robertburrelldonkin.template4couchdb.CouchDatabaseBuilder;

import org.junit.Test;

public class CouchDatabaseBuilderTest {

	@Test
	public void testDefaultHostIsLocal() {
		assertThat(CouchDatabaseBuilder.aCouchDatabase().build().getHost(), is("127.0.0.1"));
	}

	@Test
	public void testDefaultPort() {
		assertThat(CouchDatabaseBuilder.aCouchDatabase().build().getPort(), is(5984));
	}


	@Test
	public void testDefaultName() {
		assertThat(CouchDatabaseBuilder.aCouchDatabase().build().getName(), is("main"));
	}	
}
