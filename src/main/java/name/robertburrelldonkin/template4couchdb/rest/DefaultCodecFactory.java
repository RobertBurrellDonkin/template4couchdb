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

import name.robertburrelldonkin.template4couchdb.IDocumentMarshaller;
import name.robertburrelldonkin.template4couchdb.IDocumentUnmarshaller;


public class DefaultCodecFactory implements ICodecFactory {

	@Override
	public <T> DefaultResponseHandler<T> handlerFor(final IDocumentUnmarshaller<T> mapper) {
		return new DefaultResponseHandler<T>(mapper);
	}

	@Override
	public <D> DefaultContentProducer<D> producerFor(
			final IDocumentMarshaller<D> marshaller,
			final D document) {
		return new DefaultContentProducer<D>(marshaller, document);
	}

}
