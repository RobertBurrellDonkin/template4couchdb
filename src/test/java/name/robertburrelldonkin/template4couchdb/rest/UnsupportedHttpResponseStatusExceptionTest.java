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
import static org.mockito.Mockito.when;

import org.apache.http.StatusLine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UnsupportedHttpResponseStatusExceptionTest {

	private static final String REASON_PHRASE = "Why everything when a little Pete";

	private static final int STATUS_CODE = 6;
	
	@Mock
	StatusLine status;
	
	UnsupportedHttpResponseStatusException subject;
	
	@Before
	public void before() {
		when(status.getReasonPhrase()).thenReturn(REASON_PHRASE);
		when(status.getStatusCode()).thenReturn(STATUS_CODE);
		
		subject = new UnsupportedHttpResponseStatusException(status);
	}
	
	@Test
	public void testMessage() {
		assertThat(subject.getMessage(), is("CouchDB returned an unsupported HTTP status code " + STATUS_CODE + "(" + REASON_PHRASE + ")"));
	}

}
