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
import static org.junit.Assert.*;

import name.robertburrelldonkin.template4couchdb.IDocumentMarshaller;
import name.robertburrelldonkin.template4couchdb.IDocumentUnmarshaller;
import name.robertburrelldonkin.template4couchdb.rest.DefaultCodecFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCodecFactoryTest {

	public final String DOCUMENT = "{name:'sid'}";
	
	@Mock
	IDocumentMarshaller<String> marshaller;
	
	@Mock
	IDocumentUnmarshaller<String> unmarshaller;
	
	DefaultCodecFactory subject;
	
	@Before
	public void before() {
		this.subject = new DefaultCodecFactory();
	}
	
	@Test
	public void testThatHandlerForSetsMapper() {
		assertThat(this.subject.handlerFor(unmarshaller).getUnmarshaller(), is(unmarshaller));
	}
	
	@Test
	public void testThatProducerForSetsMarshaller() {
		assertThat(this.subject.producerFor(marshaller, DOCUMENT).getMarshaller(), is(marshaller));
	}
	
	@Test
	public void testThatProducerForSetsDocument() {
		assertThat(this.subject.producerFor(marshaller, DOCUMENT).getDocument(), is(DOCUMENT));
	}
}
