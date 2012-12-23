package name.robertburrelldonkin.template4couchdb.rest;

import java.io.IOException;
import java.io.InputStream;

import name.robertburrelldonkin.template4couchdb.IDocumentMapper;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

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
			final StatusLine status = response.getStatusLine();
			if (status.getStatusCode() == HttpStatus.SC_OK) {
				result = mapper.map(content);
			} else {
				EntityUtils.consume(entity);
				throw new UnsupportedHttpResponseStatusException(status);
			}
		} finally {
			IOUtils.closeQuietly(content);
		}
		return result;
	}

	public IDocumentMapper<T> getMapper() {
		return mapper;
	}

}
