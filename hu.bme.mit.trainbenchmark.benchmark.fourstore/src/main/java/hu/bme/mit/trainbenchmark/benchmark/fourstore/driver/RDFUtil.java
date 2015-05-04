package hu.bme.mit.trainbenchmark.benchmark.fourstore.driver;

import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.URIImpl;

public class RDFUtil {

	public static String brackets(final String URI) {
		if (URI.startsWith("<")) {
			return URI;
		} else {
			return "<" + URI + ">";
		}
	}

	public static String toURI(final String prefix, final long id) {
		return prefix + "x" + id;
	}
	
	public static String toLiteral(final Object o) {
		final URI uri = new URIImpl("http://www.w3.org/2001/XMLSchema#int");
		final Literal lit = new LiteralImpl(o.toString(), uri);
		return lit.toString();
	}
	
}
