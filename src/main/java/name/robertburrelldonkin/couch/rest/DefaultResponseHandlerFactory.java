package name.robertburrelldonkin.couch.rest;

import name.robertburrelldonkin.couch.IDocumentMapper;


public class DefaultResponseHandlerFactory implements IResponseHandlerFactory {

	@Override
	public <T> DefaultResponseHandler<T> handlerFor(final IDocumentMapper<T> mapper) {
		return new DefaultResponseHandler<T>(mapper);
	}

}
