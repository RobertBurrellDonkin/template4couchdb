package name.robertburrelldonkin.template4couchdb.rest;

import org.apache.http.client.ResponseHandler;

import name.robertburrelldonkin.template4couchdb.IDocumentUnmarshaller;

public interface IResponseHandlerFactory {

	<T> ResponseHandler<T> handlerFor(IDocumentUnmarshaller<T> mapper);

}
