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

import static org.junit.Assert.*;


import org.junit.Test;
import org.robertburrelldonkin.template4couchdb.CouchDatabaseBuilder;

public class CouchDatabaseTest {

	@Test
	public void testGetDataBaseURL() {
		assertThat(CouchDatabaseBuilder.aCouchDatabase().atHost("127.0.0.1").atPort(5984).withName("fun").build().getDataBaseUrl(), is("http://127.0.0.1:5984/fun"));
	}


	@Test
	public void testGetCouchURL() {
		assertThat(CouchDatabaseBuilder.aCouchDatabase().atHost("127.0.0.1").atPort(5984).withName("fun").build().getCouchUrl(), is("http://127.0.0.1:5984/"));
	}

	@Test
	public void testUrlFor() {
		assertThat(CouchDatabaseBuilder.aCouchDatabase().atHost("127.0.0.1").atPort(5984).withName("fun").build().urlFor("docId"), is("http://127.0.0.1:5984/fun/docId"));
	}
}
