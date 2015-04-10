package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations;

import java.io.IOException;
import java.util.Collection;

public abstract class TransformationAction {

	public abstract void transform(Collection<Object> objects) throws IOException;

}
