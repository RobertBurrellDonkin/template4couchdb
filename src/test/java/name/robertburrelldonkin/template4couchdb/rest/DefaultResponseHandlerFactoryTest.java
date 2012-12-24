package name.robertburrelldonkin.template4couchdb.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import name.robertburrelldonkin.template4couchdb.IDocumentUnmarshaller;
import name.robertburrelldonkin.template4couchdb.rest.DefaultResponseHandlerFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultResponseHandlerFactoryTest {

	@Mock
	IDocumentUnmarshaller<String> mapper;
	
	DefaultResponseHandlerFactory subject;
	
	@Before
	public void before() {
		this.subject = new DefaultResponseHandlerFactory();
	}
	
	@Test
	public void testThatHandlerForSetsMapper() {
		assertThat(this.subject.handlerFor(mapper).getMapper(), is(mapper));
	}
}
