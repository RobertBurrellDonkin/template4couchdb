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

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

public class FromStringOutputWriter implements IOutputWriter {

	private static final String UTF_8 = "UTF-8";
	private final String document;
	
	public FromStringOutputWriter(String document) {
		super();
		this.document = document;
	}

	@Override
	public void to(final OutputStream source) {
		try {
			IOUtils.write(document, source, UTF_8);
		} catch (IOException e) {
			throw new DocumentMappingException(e);
		}
	}

	public String getDocument() {
		return document;
	}

}
