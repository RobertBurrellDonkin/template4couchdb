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
package name.robertburrelldonkin.template4couchdb;


public class CouchDBTemplate<R> {

	private final IRestClient restClient;
	private final CouchDatabase database;
	private final IDocumentUnmarshaller<R> couchResponseUnmarshaller;

	public CouchDBTemplate(final IRestClient restClient, 
			final CouchDatabase database,
			final IDocumentUnmarshaller<R> couchResponseUnmarshaller) {
		super();
		this.restClient = restClient;
		this.database = database;
		this.couchResponseUnmarshaller = couchResponseUnmarshaller;
	}

	public void shutdown() {
		restClient.shutdown();
	}
	
	public <T> T get(final String documentId, final IDocumentUnmarshaller<T> documentUnmarshaller) {
		return restClient.get(database.urlFor(documentId), documentUnmarshaller);
	}
	
	public <T> T version(final IDocumentUnmarshaller<T> mapper) {
		return restClient.get(database.getCouchUrl(), mapper);
	}
	
	public <D> R post(final String intoDirectory, 
			final IDocumentMarshaller<D> documentMarshaller,
			final D document) {
		return postTo(database.urlFor(intoDirectory), documentMarshaller, document);
	}

	private <D> R postTo(final String url,
			final IDocumentMarshaller<D> documentMarshaller, final D document) {
		return restClient.post(
				url, 
				documentMarshaller, 
				document, couchResponseUnmarshaller);
	}

	public <D> R post(IDocumentMarshaller<D> documentMarshaller,
			D document) {
		return postTo(database.getDataBaseUrl(), documentMarshaller, document);
	}
}
