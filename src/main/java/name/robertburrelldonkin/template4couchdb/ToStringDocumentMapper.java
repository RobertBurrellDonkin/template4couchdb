package name.robertburrelldonkin.template4couchdb;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class ToStringDocumentMapper implements IDocumentMapper<String> {

	public String map(final InputStream source) { 
		try {
			return IOUtils.toString(source, "UTF-8");
		} catch (IOException e) {
			throw new DocumentMappingException(e);
		}
	}

}
