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

public class CouchDatabase {

	public static final String DEFAULT_HOST = "127.0.0.1";
	public static final int DEFAULT_PORT = 5984;
	
	private final String host;
	private final int port;
	private final String name;
	private final String url;
	
	public CouchDatabase(final String name) {
		this(DEFAULT_HOST, DEFAULT_PORT, name);
	}
	
	public CouchDatabase(final String host, final int port, final String name) {
		super();
		this.host = host;
		this.port = port;
		this.name = name;
		this.url = "http://" + host + ":" + port + "/" + name;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getName() {
		return name;
	}
	
	public String getDataBaseUrl() {
		return url;
	}
}
