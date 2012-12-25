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



import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FromStringOutputWriterTest {
	
	String document = "{hello:'world'}";
	
	FromStringOutputWriter subject;
	
	@Mock
	OutputStream mockOut;
	
	@Before
	public void before() {
		this.subject = new FromStringOutputWriter(document);
	}
	
	@Test
	public void testOutputDocument() throws UnsupportedEncodingException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		this.subject.to(out);
		assertThat(out.toString("UTF-8"), is(document));
	}
	
	@Test(expected = DocumentMappingException.class)
	public void testToShouldRethrowException() throws Exception {
		doThrow(new IOException()).when(mockOut).write((byte[]) anyObject());
		this.subject.to(mockOut);
	}

}
