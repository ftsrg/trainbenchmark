package hu.bme.mit.trainbenchmark.benchmark.matches;

import java.util.Comparator;

public class LongComparator implements Comparator<Long> {

	@Override
	public int compare(final Long l1, final Long l2) {
		return Long.compare(l1, l2);
	}

}
