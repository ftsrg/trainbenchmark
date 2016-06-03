package hu.bme.mit.trainbenchmark.benchmark.emfapi.operations;

import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EMFTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EMFAPIModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EMFAPIPosLengthQuery;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public class EMFAPIModelOperationFactory {// <TPatternMatch extends EMFMatch, TDriver extends EMFDriver> extends
											// ModelOperation<TPatternMatch, TDriver> {

	// public EMFAPIModelOperation(ModelQuery<TPatternMatch, TDriver> query,
	// Optional<ModelTransformation<TPatternMatch, TDriver>> transformation) {
	// super(query, transformation);
	// }

	// FDriver = generatic parameter for the Driver
	@SuppressWarnings("unchecked")
	public static <TDriver extends EMFDriver> ModelOperation<? extends EMFMatch, TDriver> createOperation(final String operation,
			final TDriver driver) {

		switch (operation) {
		// case "PosLength": {
		//
		// }

		// case "PosLengthInject": {
		//
		// }
		case "PosLengthRepair": {
			final EMFAPIModelQuery<? extends EMFMatch, TDriver> query = new EMFAPIPosLengthQuery<>(driver);
			final EMFTransformationRepairPosLength<TDriver> transformation = new EMFTransformationRepairPosLength<>(driver); 
		

			ModelOperation<? extends EMFMatch, TDriver> modelOperation = new ModelOperation<>(query, transformation);
			return modelOperation;
		}
		default:
			throw new UnsupportedOperationException("Operation " + operation + " not supported.");
		}
	}

}
