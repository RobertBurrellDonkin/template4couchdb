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


public class CouchDBTemplate {

	private final IRestClient restClient;
	private final CouchDatabase database;

	public CouchDBTemplate(IRestClient restClient, final CouchDatabase database) {
		super();
		this.restClient = restClient;
		this.database = database;
	}

	public void shutdown() {
		restClient.shutdown();
	}
	
	public <T> T get(final String documentId, final IDocumentUnmarshaller<T> mapper) {
		return restClient.get(database.urlFor(documentId), mapper);
	}
	
	public <T> T version(final IDocumentUnmarshaller<T> mapper) {
		return restClient.get(database.getCouchUrl(), mapper);
	}
}
