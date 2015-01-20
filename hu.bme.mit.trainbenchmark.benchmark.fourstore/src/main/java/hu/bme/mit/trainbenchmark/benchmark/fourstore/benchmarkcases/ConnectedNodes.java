package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;

import java.io.IOException;

import com.google.common.collect.Multimap;

public class ConnectedNodes extends FourStoreJavaBenchmarkCase implements TransformationBenchmarkCase {

	protected String name;
	protected String relationshipName;

	public ConnectedNodes(String name, String relationshipName) {
		this.name = name;
		this.relationshipName = relationshipName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void check() throws IOException {
		bmr.startStopper();

		Multimap<Long, Long> nodes = driver.collectEdges(relationshipName);

		bmr.addInvalid(nodes.size());
		bmr.addCheckTime();
	}

	@Override
	public void modify() throws IOException {
	}

}
