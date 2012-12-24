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

public interface IRestClient {

	<T> T get(final String url, final IDocumentUnmarshaller<T> unmarshaller);
	
	<R, D> R post(final String url, 
			final IDocumentMarshaller<D> documentMarshaller,
			final D document,
			final IDocumentUnmarshaller<R> responseUnmarshaller);

	void shutdown();
}
