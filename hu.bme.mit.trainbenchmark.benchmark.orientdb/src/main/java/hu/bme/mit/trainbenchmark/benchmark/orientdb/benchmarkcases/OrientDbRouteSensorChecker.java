package hu.bme.mit.trainbenchmark.benchmark.orientdb.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbRouteSensorMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbRouteSensorChecker extends OrientDbChecker<OrientDbRouteSensorMatch> {

	public OrientDbRouteSensorChecker(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public Collection<OrientDbRouteSensorMatch> check() throws IOException {
		
		final Collection<OrientDbRouteSensorMatch> matches = new ArrayList<OrientDbRouteSensorMatch>();
		List<String> lines = FileUtils.readLines(FileUtils.getFile(queryPath + "RouteSensor.gremlin"));
		List<Row> result = driver.runQuery(lines);
		
		for (Row row : result) {
			matches.add(new OrientDbRouteSensorMatch(row));
		}
		
		return matches;
	}

}
