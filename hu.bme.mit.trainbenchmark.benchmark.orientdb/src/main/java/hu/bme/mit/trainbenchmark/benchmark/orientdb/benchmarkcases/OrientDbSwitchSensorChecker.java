package hu.bme.mit.trainbenchmark.benchmark.orientdb.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbSwitchSensorMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbSwitchSensorChecker extends OrientDbChecker<OrientDbSwitchSensorMatch> {

	public OrientDbSwitchSensorChecker(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public Collection<OrientDbSwitchSensorMatch> check() throws IOException {
		
		final Collection<OrientDbSwitchSensorMatch> matches = new ArrayList<OrientDbSwitchSensorMatch>();
		List<String> lines = FileUtils.readLines(FileUtils.getFile(queryPath + "SwitchSensor.gremlin"));
		List<Row> result = driver.runQuery(lines);
		
		for (Row row : result) {
			matches.add(new OrientDbSwitchSensorMatch(row));
		}
		
		return matches;
	}

}
