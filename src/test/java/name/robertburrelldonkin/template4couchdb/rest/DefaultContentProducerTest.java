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

import static org.mockito.Mockito.*;

import java.io.OutputStream;

import name.robertburrelldonkin.template4couchdb.IDocumentMarshaller;
import name.robertburrelldonkin.template4couchdb.IOutputWriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultContentProducerTest {

	String document = "{name='henry'}";
	
	@Mock
	OutputStream outstream;
	
	@Mock
	IOutputWriter writer;
	
	@Mock
	IDocumentMarshaller<String> marshaller;
	
	DefaultContentProducer<String> subject;
	
	@Before
	public void before() {
		when(marshaller.write(document)).thenReturn(writer);
		this.subject = new DefaultContentProducer<String>(marshaller, document);
	}
	
	@Test
	public void testDelegateToMarshaller() {
		this.subject.writeTo(outstream);
		verify(this.marshaller).write(document);
	}
	
	@Test
	public void testDelegateToWriter() {
		this.subject.writeTo(outstream);
		verify(this.writer).to(outstream);	
	}
}
