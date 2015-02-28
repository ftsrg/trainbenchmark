package hu.bme.mit.trainbenchmark.benchmark.jena.driver;

import hu.bme.mit.trainbenchmark.rdf.RDFHelper;

import java.util.Comparator;

import com.hp.hpl.jena.rdf.model.Resource;

public class JenaComparator implements Comparator<Resource> {

	@Override
	public int compare(final Resource r1, final Resource r2) {
		final String uri1 = r1.getURI();
		final String uri2 = r2.getURI();
		final long id1 = RDFHelper.extractId(uri1);
		final long id2 = RDFHelper.extractId(uri2);
		
		return Long.compare(id1, id2);
	}

}
