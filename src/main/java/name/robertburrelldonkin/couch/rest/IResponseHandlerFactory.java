package name.robertburrelldonkin.couch.rest;

import org.apache.http.client.ResponseHandler;

import name.robertburrelldonkin.couch.IDocumentMapper;

public interface IResponseHandlerFactory {

	<T> ResponseHandler<T> handlerFor(IDocumentMapper<T> mapper);

}
