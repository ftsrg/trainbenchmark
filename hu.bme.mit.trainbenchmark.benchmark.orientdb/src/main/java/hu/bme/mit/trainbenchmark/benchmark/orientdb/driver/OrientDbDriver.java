package hu.bme.mit.trainbenchmark.benchmark.orientdb.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Lists;
import com.orientechnologies.orient.core.exception.OConcurrentModificationException;
import com.orientechnologies.orient.graph.gremlin.OCommandGremlin;
import com.orientechnologies.orient.graph.gremlin.OGremlinHelper;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLReader;
import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbDriver extends Driver<Vertex> {

	protected OrientGraph graphDb;
	protected String dbPath;
	protected String benchmarkDir;
	protected Comparator<Vertex> vertexComparator = new VertexComparator();

	public OrientDbDriver(final String dbPath, final String benchmarkDir) throws IOException {
		// delete old directory
		if (new File(dbPath).exists()) {
			FileUtils.deleteDirectory(new File(dbPath));
		}

		// start the database
		this.dbPath = dbPath;
		this.benchmarkDir = benchmarkDir;
	}

	@Override
	public void beginTransaction() {
		graphDb.begin();
	}

	@Override
	public void finishTransaction() {
		for (int retry = 0; retry < 10; ++retry) {
			try {
				graphDb.commit();
				break;
			} catch (final OConcurrentModificationException e) {
			}
		}
	}

	@Override
	public void read(final String filePath) throws IOException {
		graphDb = new OrientGraph("plocal:" + dbPath);
		final GraphMLReader graphMLReader = new GraphMLReader(graphDb);
		graphMLReader.inputGraph(filePath + ".graphml", 100);
	}

	public List<Row> runQuery(final List<String> lines) throws IOException {
		OGremlinHelper.global().create();
		final OCommandGremlin gremcomm = new OCommandGremlin();
		List<Row> result = new ArrayList<Row>();
		for (final String line : lines) {
			if (lines.indexOf(line) != lines.size() - 1) {
				gremcomm.setText(line).execute();
			} else {
				result = gremcomm.setText(line).execute();
			}
		}
		return result;
	}

	@Override
	public Collection<?> runQuery(final Query query, final String queryDefinition) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		graphDb.drop();
	}

	public String typeTranslator(final String type) {
		String result;
		switch (type) {
		case "Segment":
			result = ":TrackElement:Segment";
			break;
		case "Signal":
			result = ":Signal";
			break;
		case "Sensor":
			result = ":Sensor";
			break;
		case "Switch":
			result = ":Switch:TrackElement";
			break;
		case "Route":
			result = ":Route";
			break;
		case "SwitchPosition":
			result = ":SwitchPosition";
			break;
		default:
			result = type;
			break;
		}
		return result;
	}

	// read

	@Override
	public List<Vertex> collectVertices(final String type) {
		final Iterable<Vertex> vertices = graphDb.getVertices("labels", typeTranslator(type));
		final List<Vertex> list = Lists.newArrayList(vertices);
		return list;
	}

	// utility

	public OrientGraph getGraphDb() {
		return graphDb;
	}

	@Override
	public Comparator<Vertex> getElementComparator() {
		return vertexComparator;
	}

	@Override
	public String getExtension() {
		return ".graphml";
	}

	public String getBenchmarkDir() {
		return benchmarkDir;
	}

}
