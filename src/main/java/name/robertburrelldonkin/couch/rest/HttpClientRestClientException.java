package name.robertburrelldonkin.couch.rest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class HttpClientRestClientException extends RuntimeException {

	private static final long serialVersionUID = -231843790463661128L;

	public HttpClientRestClientException(final ClientProtocolException e) {
		super(e);
	}

	public HttpClientRestClientException(final IOException e) {
		super(e);
	}

}
