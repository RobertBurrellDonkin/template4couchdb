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

import name.robertburrelldonkin.template4couchdb.IDocumentUnmarshaller;
import name.robertburrelldonkin.template4couchdb.IRestClient;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;

public class HttpClientRestClient implements IRestClient {

	private final IResponseHandlerFactory responseHandlerFactory;
	
	private final HttpClient httpClient;
	
	public HttpClientRestClient(final HttpClient httpClient, 
			final IResponseHandlerFactory responseHandlerFactory) {
		super();
		this.httpClient = httpClient;
		this.responseHandlerFactory = responseHandlerFactory;
	}

	public <T> T get(final String url, final IDocumentUnmarshaller<T> mapper) {
		final ResponseHandler<T> handler = responseHandlerFactory.handlerFor(mapper);
		try {
			return httpClient.execute(new HttpGet(url), handler);
		} catch (ClientProtocolException e) {
			throw new HttpClientRestClientException(e);
		} catch (IOException e) {
			throw new HttpClientRestClientException(e);
		}
	}


	public void shutdown() {
		final ClientConnectionManager connectionManager = this.httpClient.getConnectionManager();
		if (connectionManager != null) {
			connectionManager.shutdown();
		}
	}
}
