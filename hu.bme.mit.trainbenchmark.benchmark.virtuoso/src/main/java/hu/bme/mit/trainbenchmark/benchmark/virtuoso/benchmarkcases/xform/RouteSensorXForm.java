package hu.bme.mit.trainbenchmark.benchmark.virtuoso.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.virtuoso.util.SesameData;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.benchmark.virtuoso.benchmarkcases.RouteSensor;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;



public class RouteSensorXForm extends RouteSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		final long nElemToModify = Util.calcModify(vbc, vbc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);
		// modify
		final long start = System.nanoTime();

		final ValueFactory f = myRepository.getValueFactory();

		try {

			final List<URI> sensors = Transformation.pickRandom(nElemToModify, invalids);
			final List<SesameData> itemsToRemove = new ArrayList<SesameData>();

			for (final URI sensor : sensors) {
				final URI sensorTE = f.createURI(RDFConstants.BASE_PREFIX + ModelConstants.TRACKELEMENT_SENSOR);
				final RepositoryResult<Statement> statementsToRemove = con.getStatements(null, sensorTE, sensor, true);
				final List<Statement> statementsToRemoveList = statementsToRemove.asList();

				for (final Statement s : statementsToRemoveList) {
					final SesameData jd = new SesameData();
					jd.setStatement(s);
					itemsToRemove.add(jd);
				}
			}

			// edit
			final long startEdit = System.nanoTime();
			for (final SesameData jd : itemsToRemove) {
				con.remove(jd.getStatement());
			}
			con.commit();
			final long end = System.nanoTime();
			bmr.addEditTime(end - startEdit);
			bmr.addModificationTime(end - start);
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
		
	}

}
