package name.robertburrelldonkin.couch.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import name.robertburrelldonkin.couch.IDocumentMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultResponseHandlerFactoryTest {

	@Mock
	IDocumentMapper<String> mapper;
	
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
