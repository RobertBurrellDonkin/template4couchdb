package name.robertburrelldonkin.template4couchdb.rest;

import java.io.OutputStream;

import name.robertburrelldonkin.template4couchdb.IDocumentMarshaller;

import org.apache.http.entity.ContentProducer;


public class DefaultContentProducer<D> implements ContentProducer {

	private final IDocumentMarshaller<D> marshaller;
	private final D document;
	
	public DefaultContentProducer(final IDocumentMarshaller<D> marshaller, D document) {
		this.marshaller = marshaller;
		this.document = document;
	}

	@Override
	public void writeTo(final OutputStream outstream) {
		marshaller.write(document).to(outstream);
	}
}
