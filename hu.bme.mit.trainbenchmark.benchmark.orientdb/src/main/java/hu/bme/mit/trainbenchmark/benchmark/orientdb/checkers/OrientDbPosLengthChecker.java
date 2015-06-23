package hu.bme.mit.trainbenchmark.benchmark.orientdb.checkers;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbPosLengthMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbPosLengthChecker extends OrientDbChecker<OrientDbPosLengthMatch> {

	public OrientDbPosLengthChecker(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}
	
	@Override
	public Collection<OrientDbPosLengthMatch> check() throws IOException {
		
		final Collection<OrientDbPosLengthMatch> matches = new ArrayList<OrientDbPosLengthMatch>();
		List<String> lines = FileUtils.readLines(FileUtils.getFile(queryPath + "PosLength.gremlin"));
		List<Row> result = driver.runQuery(lines);
		
		for (Row row : result) {
			matches.add(new OrientDbPosLengthMatch(row));
		}
		
		return matches;
	}
}
