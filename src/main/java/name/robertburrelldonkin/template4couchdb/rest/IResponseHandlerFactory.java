package name.robertburrelldonkin.template4couchdb.rest;

import org.apache.http.client.ResponseHandler;

import name.robertburrelldonkin.template4couchdb.IDocumentMapper;

public interface IResponseHandlerFactory {

	<T> ResponseHandler<T> handlerFor(IDocumentMapper<T> mapper);

}
