package name.robertburrelldonkin.couch.rest;

import java.io.IOException;
import java.io.InputStream;

import name.robertburrelldonkin.couch.IDocumentMapper;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

public class DefaultResponseHandler<T> implements ResponseHandler<T> {

	private final IDocumentMapper<T> mapper;
	
	public DefaultResponseHandler(final IDocumentMapper<T> mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public T handleResponse(final HttpResponse response) throws ClientProtocolException,
			IOException {
		final HttpEntity entity = response.getEntity();
		final InputStream content = entity.getContent();
		final T result;
		try {
			result = mapper.map(content);
		} finally {
			IOUtils.closeQuietly(content);
		}
		return result;
	}

	public IDocumentMapper<T> getMapper() {
		return mapper;
	}

}
