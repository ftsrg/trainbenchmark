//package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;
//
//import java.io.IOException;
//
//import com.google.common.collect.Multimap;
//
//public class ConnectedNodes extends FourStoreSimpleBenchmarkCase {
//
//	protected String name;
//	protected String relationshipName;
//
//	protected Multimap<Long, Long> edges; 
//	
//	public ConnectedNodes(String name, String relationshipName) {
//		this.name = name;
//		this.relationshipName = relationshipName;
//	}
//
//	@Override
//	public String getName() {
//		return name;
//	}
//
//	@Override
//	public void check() throws IOException {
//		bmr.startStopper();
//
//		edges = driver.collectEdges(relationshipName);
//		
//		bmr.addInvalid(edges.size());
//		bmr.addCheckTime();
//	}
//	
//	@Override
//	public int getResultSize() {
//		return edges.size();
//	}
//}
