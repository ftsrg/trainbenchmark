package hu.bme.mit.trainbenchmark.benchmark.orientdb.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbSemaphoreNeighborMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbSemaphoreNeighborChecker extends OrientDbChecker<OrientDbSemaphoreNeighborMatch> {

	public OrientDbSemaphoreNeighborChecker(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public Collection<OrientDbSemaphoreNeighborMatch> check() throws IOException {
		
		final Collection<OrientDbSemaphoreNeighborMatch> matches = new ArrayList<OrientDbSemaphoreNeighborMatch>();
		List<String> lines = FileUtils.readLines(FileUtils.getFile(queryPath + "SemaphoreNeighbor.gremlin"));
		List<Row> result = driver.runQuery(lines);
		
		for (Row row : result) {
			matches.add(new OrientDbSemaphoreNeighborMatch(row));
		}
		
		return matches;
	}

}
