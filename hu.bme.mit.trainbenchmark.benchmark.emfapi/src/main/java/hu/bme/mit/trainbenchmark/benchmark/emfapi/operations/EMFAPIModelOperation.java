//package hu.bme.mit.trainbenchmark.benchmark.emfapi.operations;
//
//import java.util.Optional;
//
//import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EMFPosLengthMatch;
//import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EMFTransformationRepairPosLength;
//import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EMFAPIModelQuery;
//import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EMFAPIPosLengthChecker;
//import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
//import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
//import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
//
//public abstract class EMFAPIModelOperation<ModelFragment> extends ModelOperation<ModelFragment> {
//
//	protected EMFAPIModelOperation(ModelQuery<ModelFragment> query, Optional<ModelTransformation<ModelFragment>> transformation) {
//		super(query, transformation);
//	}
//
//	public EMFAPIModelOperation<ModelFragment> createOperation(final String operation) {
//		switch (operation) {
//		case "PosLength":
//			
//			
//			EMFAPIModelOperation<SesamePosLengthMatch> x;
//			
//			
//			break;
//		case "PosLengthInject":
//			
//			
//			
//			break;
//		case "PosLengthRepair":
//			final EMFAPIModelQuery<EMFPosLengthMatch> modelQuery = new EMFAPIPosLengthChecker(null);
//			final EMFTransformationRepairPosLength transformation = new EMFTransformationRepairPosLength(null);
//			
//			
//			ModelOperation.of(modelQuery, transformation);
//			
//			break;
//		default:
//			throw new UnsupportedOperationException("Operation " + operation + " not supported.");
//		}
//	}
//	
//}
