package hu.bme.mit.trainbenchmark.benchmark.matches;

import java.util.Arrays;
import java.util.Objects;

public abstract class BaseMatch implements Match {

	@Override
	public boolean equals(final Object obj) {
		return Arrays.equals(toArray(), ((Match) obj).toArray());
	}

	@Override
	public int hashCode() {
		return Objects.hash(toArray());
	}

}
