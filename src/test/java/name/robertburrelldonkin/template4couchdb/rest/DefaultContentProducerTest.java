package name.robertburrelldonkin.template4couchdb.rest;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

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
