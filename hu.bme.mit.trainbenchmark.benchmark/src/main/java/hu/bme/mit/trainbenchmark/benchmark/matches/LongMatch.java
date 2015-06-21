package hu.bme.mit.trainbenchmark.benchmark.matches;

import java.util.Arrays;

/**
 * A match class for storing only the ids of the elements in the match.
 * 
 * @author szarnyasg
 * 
 */
public abstract class LongMatch {

	protected Long[] match;

	public LongMatch() {
		super();
	}

	public Long[] getMatch() {
		return match;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(match);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final LongMatch other = (LongMatch) obj;
		if (!Arrays.equals(match, other.match))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LongMatch [match=" + Arrays.toString(match) + "]";
	}

}