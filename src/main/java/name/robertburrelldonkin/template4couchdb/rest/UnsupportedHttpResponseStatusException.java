package name.robertburrelldonkin.template4couchdb.rest;

import org.apache.http.StatusLine;

public class UnsupportedHttpResponseStatusException extends RuntimeException {

	private static final long serialVersionUID = -753235933346384114L;

	private final String reasonPhrase;
	private int statusCode;
	
	public UnsupportedHttpResponseStatusException(final StatusLine status) {
		this(status.getReasonPhrase(), status.getStatusCode());
	}

	public UnsupportedHttpResponseStatusException(String reasonPhrase,
			int statusCode) {
		super();
		this.reasonPhrase = reasonPhrase;
		this.statusCode = statusCode;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	public int getStatusCode() {
		return statusCode;
	}
}

