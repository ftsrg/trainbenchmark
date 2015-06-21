package hu.bme.mit.trainbenchmark.benchmark.jena.driver;

import java.util.Comparator;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

public class StatemetComparator implements Comparator<Statement> {

	protected final ResourceComparator resourceComparator = new ResourceComparator();

	@Override
	public int compare(final Statement o1, final Statement o2) {
		final Resource r1 = o1.getObject().asResource();
		final Resource r2 = o2.getObject().asResource();

		return resourceComparator.compare(r1, r2);
	}
}
