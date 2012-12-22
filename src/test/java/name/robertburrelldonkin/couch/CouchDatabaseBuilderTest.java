package name.robertburrelldonkin.couch;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CouchDatabaseBuilderTest {

	@Test
	public void testDefaultHostIsLocal() {
		assertThat(CouchDatabaseBuilder.aCouchDatabase().build().getHost(), is("127.0.0.1"));
	}

	@Test
	public void testDefaultPort() {
		assertThat(CouchDatabaseBuilder.aCouchDatabase().build().getPort(), is(5984));
	}


	@Test
	public void testDefaultName() {
		assertThat(CouchDatabaseBuilder.aCouchDatabase().build().getName(), is("main"));
	}	
}
