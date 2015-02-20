//package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;
//
//import hu.bme.mit.trainbenchmark.constants.ModelConstants;
//import hu.bme.mit.trainbenchmark.rdf.RDFConstants;
//
//import java.io.IOException;
//import java.util.Set;
//
//import com.google.common.collect.Sets;

//public class SwitchNodes extends FourStoreComplexBenchmarkCase {
//
//	protected Set<Long> switches; 
//	
//	@Override
//	public String getName() {
//		return "SwitchNodes";
//	}
//
//	@Override
//	public void check() throws IOException {
//		bmr.startStopper();
//
//		switches = Sets.newHashSet(driver.collectVertices(RDFConstants.BASE_PREFIX + ModelConstants.SWITCH));
//
//		bmr.addInvalid(switches.size());
//		bmr.addCheckTime();
//	}
//
//	@Override
//	public int getResultSize() {
//		return switches.size();
//	}
//	
//}
