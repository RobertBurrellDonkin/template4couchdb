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
package org.robertburrelldonkin.template4couchdb.rest;

import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.ContentProducer;
import org.robertburrelldonkin.template4couchdb.IDocumentMarshaller;
import org.robertburrelldonkin.template4couchdb.IDocumentUnmarshaller;


public interface ICodecFactory {

	<T> ResponseHandler<T> handlerFor(final IDocumentUnmarshaller<T> unmarshaller);

	<D> ContentProducer producerFor(final IDocumentMarshaller<D> marshaller, final D document);
}
