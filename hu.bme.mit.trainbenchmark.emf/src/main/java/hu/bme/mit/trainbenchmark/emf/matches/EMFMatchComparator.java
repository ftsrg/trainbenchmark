package hu.bme.mit.trainbenchmark.emf.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.MatchComparator;
import hu.bme.mit.trainbenchmark.emf.RailwayElementComparator;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public class EMFMatchComparator extends MatchComparator<EMFMatch, RailwayElement> {

	protected RailwayElementComparator rec = new RailwayElementComparator();

	@Override
	public int compare(final EMFMatch o1, final EMFMatch o2) {
		final RailwayElement[] m1 = o1.match;
		final RailwayElement[] m2 = o2.match;
		return compareArrays(m1, m2, rec);
	}

}
