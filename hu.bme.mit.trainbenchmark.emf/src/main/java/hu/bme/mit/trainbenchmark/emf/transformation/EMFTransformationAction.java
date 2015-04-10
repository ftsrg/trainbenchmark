package hu.bme.mit.trainbenchmark.emf.transformation;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationAction;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public abstract class EMFTransformationAction extends TransformationAction {

	protected EMFDriver<?> driver;

}
