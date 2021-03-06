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
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.robertburrelldonkin.template4couchdb.DocumentMappingException;
import org.robertburrelldonkin.template4couchdb.StringDocumentMapper;

@RunWith(MockitoJUnitRunner.class)
public class StringDocumentMapperTest {

	@Mock
	InputStream source;
	
	StringDocumentMapper subject;
	
	@Before
	public void before() {
		this.subject = new StringDocumentMapper();
	}
	
	@Test
	public void testMap() {
		String string = "{name: me}";
		assertThat(this.subject.from(new ByteArrayInputStream(string.getBytes())), is(string));
	}

	@Test(expected = DocumentMappingException.class)
	public void testThatFromIOExceptionIsRethrown() throws IOException {
		when(source.read()).thenThrow(new IOException());
		when(source.available()).thenThrow(new IOException());
		when(source.read((byte[]) anyObject())).thenThrow(new IOException());
		this.subject.from(source);
	}
	
	@Test
	public void testThatWriteSetsDocument() {
		String document = "{hello: 'world'}";
		assertThat(this.subject.write(document).getDocument(), is(document));
	}
}
