package name.robertburrelldonkin.template4couchdb.rest;

import name.robertburrelldonkin.template4couchdb.IDocumentUnmarshaller;


public class DefaultResponseHandlerFactory implements IResponseHandlerFactory {

	@Override
	public <T> DefaultResponseHandler<T> handlerFor(final IDocumentUnmarshaller<T> mapper) {
		return new DefaultResponseHandler<T>(mapper);
	}

}
