//package hu.bme.mit.trainbenchmark.benchmark.sesame.operations;
//
//import java.util.Optional;
//
//import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
//import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
//import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
//import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesamePosLengthMatch;
//
//public abstract class SesameModelOperation<ModelFragment> extends ModelOperation<ModelFragment> {
//
//	protected SesameModelOperation(ModelQuery<ModelFragment> query, Optional<ModelTransformation<ModelFragment>> transformation) {
//		super(query, transformation);
//	}
//
//	public SesameModelOperation<ModelFragment> createOperation(final String operation) {
//		switch (operation) {
//		case "PosLength":
//			
//			
//			SesameModelOperation<SesamePosLengthMatch> x;
//			
//			
//			break;
//		case "PosLengthInject":
//			
//			break;
//		case "PosLengthRepair":
//			
//			
//			
//			break;
//		default:
//			throw new UnsupportedOperationException("Operation " + operation + " not supported.");
//		}
//	}
//	
//}
