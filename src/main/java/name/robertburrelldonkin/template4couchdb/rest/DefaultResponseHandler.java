/*
* 	Copyright 2012 Robert Burrell Donkin robertburrelldonkin.name
* 
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/
package name.robertburrelldonkin.template4couchdb.rest;

import java.io.IOException;
import java.io.InputStream;

import name.robertburrelldonkin.template4couchdb.IDocumentUnmarshaller;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

public class DefaultResponseHandler<T> implements ResponseHandler<T> {

	private final IDocumentUnmarshaller<T> mapper;
	
	public DefaultResponseHandler(final IDocumentUnmarshaller<T> mapper) {
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
				result = mapper.from(content);
			} else {
				EntityUtils.consume(entity);
				throw new UnsupportedHttpResponseStatusException(status);
			}
		} finally {
			IOUtils.closeQuietly(content);
		}
		return result;
	}

	public IDocumentUnmarshaller<T> getUnmarshaller() {
		return mapper;
	}

}
