package hu.bme.mit.trainbenchmark.benchmark.java.transformation;

import java.util.Collection;

public abstract class JavaTransformation<M> {

	public abstract void transform(Collection<M> matchers);

}
