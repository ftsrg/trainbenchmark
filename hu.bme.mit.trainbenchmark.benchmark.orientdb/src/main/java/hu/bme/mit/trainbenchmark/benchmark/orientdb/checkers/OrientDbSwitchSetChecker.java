package hu.bme.mit.trainbenchmark.benchmark.orientdb.checkers;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbSwitchSetMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbSwitchSetChecker extends OrientDbChecker<OrientDbSwitchSetMatch>{

	public OrientDbSwitchSetChecker(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public Collection<OrientDbSwitchSetMatch> check() throws IOException {
		
		final Collection<OrientDbSwitchSetMatch> matches = new ArrayList<OrientDbSwitchSetMatch>();
		List<String> lines = FileUtils.readLines(FileUtils.getFile(queryPath + "SwitchSet.gremlin"));
		List<Row> result = driver.runQuery(lines);
		
		for (Row row : result) {
			matches.add(new OrientDbSwitchSetMatch(row));
		}
		
		return matches;
	}

}
