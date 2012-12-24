package name.robertburrelldonkin.template4couchdb;

public interface IDocumentMarshaller<D> {

	IOutputWriter write(final D document);
}
