package hu.bme.mit.trainbenchmark.benchmark.sesame.matches;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;

public abstract class SesameMatch {

	protected BindingSet bs;

	public SesameMatch(final BindingSet bs) {
		this.bs = bs;
	}

	public abstract Value[] toArray();

}
