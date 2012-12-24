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

import java.io.OutputStream;

import name.robertburrelldonkin.template4couchdb.IDocumentMarshaller;

import org.apache.http.entity.ContentProducer;


public class DefaultContentProducer<D> implements ContentProducer {

	private final IDocumentMarshaller<D> marshaller;
	private final D document;
	
	public DefaultContentProducer(final IDocumentMarshaller<D> marshaller, final D document) {
		this.marshaller = marshaller;
		this.document = document;
	}

	@Override
	public void writeTo(final OutputStream outstream) {
		marshaller.write(document).to(outstream);
	}

	public IDocumentMarshaller<D> getMarshaller() {
		return marshaller;
	}

	public D getDocument() {
		return document;
	}
}
