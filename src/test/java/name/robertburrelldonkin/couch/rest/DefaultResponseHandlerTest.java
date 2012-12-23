package name.robertburrelldonkin.couch.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;

import name.robertburrelldonkin.couch.IDocumentMapper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultResponseHandlerTest {

	public final String OUTPUT = "{}";
	
	@Mock
	InputStream inputStream;
	
	@Mock
	HttpEntity entity;
	
	@Mock
	HttpResponse response;
	
	@Mock
	IDocumentMapper<String> mapper;
	
	DefaultResponseHandler<String> subject;
	
	@Before
	public void before() throws Exception {
		when(response.getEntity()).thenReturn(entity);
		when(entity.getContent()).thenReturn(inputStream);
		when(mapper.map(inputStream)).thenReturn(OUTPUT);
		
		this.subject = new DefaultResponseHandler<String>(mapper);
	}
	
	@Test
	public void testHandleResponseInputsResponseToMapper() throws Exception {
		this.subject.handleResponse(response);
		verify(mapper).map(inputStream);
	}
	
	@Test
	public void testHandleResponseResult() throws Exception {
		assertThat(this.subject.handleResponse(response), is(OUTPUT));
	}

	@Test
	public void testHandleResponseClosesStream() throws Exception {
		this.subject.handleResponse(response);
		verify(inputStream).close();
	}
	
	@Test
	public void testHandleResponseClosesStreamAfterException() throws Exception {
		when(this.mapper.map(inputStream)).thenThrow(new IllegalArgumentException());
		try {
		this.subject.handleResponse(response);
		} catch (IllegalArgumentException e){
			// expected
		}
		verify(inputStream).close();
	}
}