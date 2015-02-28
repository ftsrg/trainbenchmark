package hu.bme.mit.trainbenchmark.emf;

import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.util.Comparator;

public class EMFComparator implements Comparator<RailwayElement> {

	@Override
	public int compare(final RailwayElement e1, final RailwayElement e2) {
		final long id1 = e1.getId();
		final long id2 = e2.getId();
		return Long.compare(id1, id2);
	}

}
