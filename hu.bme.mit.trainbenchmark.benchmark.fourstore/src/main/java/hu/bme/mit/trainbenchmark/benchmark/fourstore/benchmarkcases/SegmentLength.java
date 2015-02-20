//package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;
//
//import hu.bme.mit.trainbenchmark.constants.ModelConstants;
//import hu.bme.mit.trainbenchmark.rdf.RDFConstants;
//
//import java.io.IOException;
//
//import com.google.common.collect.Multimap;
//
//public class SegmentLength extends FourStoreSimpleBenchmarkCase {
//
//	protected Multimap<Long, Object> properties;
//	
//	@Override
//	public String getName() {
//		return "SegmentLength";
//	} 
//
//	@Override
//	public void check() throws IOException {
//		bmr.startStopper();
//
//		String t = RDFConstants.BASE_PREFIX + ModelConstants.SEGMENT_LENGTH;
//		properties = driver.collectProperties(t);
//
//		bmr.addInvalid(properties.size());
//		bmr.addCheckTime();
//	}
//	
//	@Override
//	public int getResultSize() {
//		return properties.size();
//	}
//}
