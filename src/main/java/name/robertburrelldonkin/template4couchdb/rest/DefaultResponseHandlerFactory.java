package name.robertburrelldonkin.template4couchdb.rest;

import name.robertburrelldonkin.template4couchdb.IDocumentMapper;


public class DefaultResponseHandlerFactory implements IResponseHandlerFactory {

	@Override
	public <T> DefaultResponseHandler<T> handlerFor(final IDocumentMapper<T> mapper) {
		return new DefaultResponseHandler<T>(mapper);
	}

}
