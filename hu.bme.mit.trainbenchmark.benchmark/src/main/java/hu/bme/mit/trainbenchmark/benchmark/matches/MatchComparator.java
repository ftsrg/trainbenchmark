package hu.bme.mit.trainbenchmark.benchmark.matches;

import java.util.Comparator;

public abstract class MatchComparator<M, T> implements Comparator<M> {

	public int compareArrays(final Object[] m1, final Object[] m2, final Comparator<T> elementComparator) {
		for (int i = 0; i < m1.length; i++) {
			final T t1 = (T) m1[i];
			final T t2 = (T) m2[i];

			final int comparison = elementComparator.compare(t1, t2);
			if (comparison != 0) {
				return comparison;
			}
		}

		return 0;
	}

}
