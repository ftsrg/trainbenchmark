package hu.bme.mit.trainbenchmark.benchmark.sesame.driver;

import java.util.Comparator;

import org.openrdf.model.URI;

public class URIComparator implements Comparator<URI> {

	@Override
	public int compare(final URI uri1, final URI uri2) {
		final long id1 = Long.parseLong(uri1.getLocalName());
		final long id2 = Long.parseLong(uri2.getLocalName());
		return Long.compare(id1, id2);
	}

}
