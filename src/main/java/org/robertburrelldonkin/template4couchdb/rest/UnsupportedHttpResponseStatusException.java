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

import static java.text.MessageFormat.*;

import org.apache.http.StatusLine;

public class UnsupportedHttpResponseStatusException extends RuntimeException {

	private static final long serialVersionUID = -753235933346384114L;

	private static final String MESSAGE_TEMPLATE = "CouchDB returned an unsupported HTTP status code {0} [{1}]";
	
	private final String reasonPhrase;
	private int statusCode;
	
	public UnsupportedHttpResponseStatusException(final StatusLine status) {
		this(status.getReasonPhrase(), status.getStatusCode());
	}

	public UnsupportedHttpResponseStatusException(final String reasonPhrase,
			final int statusCode) {
		super(format(MESSAGE_TEMPLATE, statusCode, reasonPhrase));
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

