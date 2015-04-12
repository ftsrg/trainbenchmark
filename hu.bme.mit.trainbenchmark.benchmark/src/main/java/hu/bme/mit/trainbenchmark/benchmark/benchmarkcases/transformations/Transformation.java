package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations;

import java.io.IOException;
import java.util.Collection;

public abstract class Transformation<O> {

	public abstract void rhs(Collection<O> objects) throws IOException;

}
