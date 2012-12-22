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
package name.robertburrelldonkin.couch;

import static name.robertburrelldonkin.couch.CouchDatabase.*;

public class CouchDatabaseBuilder {

	public static CouchDatabaseBuilder aCouchDatabase() {
		return new CouchDatabaseBuilder();
	}

	private String host = DEFAULT_HOST;
	private int port = DEFAULT_PORT;
	private String name = "main";
	
	private CouchDatabaseBuilder() {
	}
	
	public CouchDatabaseBuilder atHost(final String host) {
		this.host = host;
		return this;
	}
	
	public CouchDatabaseBuilder atPort(final int port) {
		this.port = port;
		return this;
	}
	
	public CouchDatabaseBuilder withName(final String name) {
		this.name = name;
		return this;
	}
	
	public CouchDatabase build() {
		return new CouchDatabase(host, port, name);
	}
}
