package hu.bme.mit.trainbenchmark.sql.match;

import java.util.Arrays;

public abstract class SQLMatch {

	protected Long[] match;

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
		final SQLMatch other = (SQLMatch) obj;
		if (!Arrays.equals(match, other.match))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SQLMatch [match=" + Arrays.toString(match) + "]";
	}

}
