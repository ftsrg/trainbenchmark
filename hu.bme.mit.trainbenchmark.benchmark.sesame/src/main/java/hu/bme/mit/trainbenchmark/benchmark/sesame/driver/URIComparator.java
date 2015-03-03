package hu.bme.mit.trainbenchmark.benchmark.sesame.driver;

import java.util.Comparator;

import org.openrdf.model.URI;

public class URIComparator implements Comparator<URI> {

	@Override
	public int compare(final URI uri1, final URI uri2) {
		final long id1 = Long.parseLong(uri1.getLocalName().substring(1));
		final long id2 = Long.parseLong(uri2.getLocalName().substring(1));
		return Long.compare(id1, id2);
	}

}
