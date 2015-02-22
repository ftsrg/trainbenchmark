package hu.bme.mit.trainbenchmark.benchmark.allegro.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.allegro.benchmarkcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.allegro.util.SesameData;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

public class RouteSensorUser extends RouteSensor implements TransformationBenchmarkCase{

	@Override
	public void modify() throws IOException {
		final long nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);
		// modify
		final long start = System.nanoTime();

		final ValueFactory f = myRepository.getValueFactory();
		final URI routeOC = f.createURI(RDFConstants.BASE_PREFIX + ModelConstants.ROUTE);
		RepositoryResult<Statement> routesIter;

		try {
			routesIter = con.getStatements(null, RDF.TYPE, routeOC, true);

			final List<Resource> routes = new ArrayList<Resource>();
			for (final Statement s : routesIter.asList()) {
				routes.add(s.getSubject());
			}
			
			final List<Resource> routesToModify = Transformation.pickRandom(nElemToModify, routes);
			final List<SesameData> itemsToRemove = new ArrayList<SesameData>();

			for (final Resource route : routesToModify) {
				final URI routeDefinition = f.createURI(RDFConstants.BASE_PREFIX + ModelConstants.ROUTE);
				final RepositoryResult<Statement> statementsToRemove = con.getStatements(route, routeDefinition, null, true);

				if (statementsToRemove.hasNext()) {
					final Statement statementToRemove = statementsToRemove.next();
					final SesameData jd = new SesameData();
					jd.setStatement(statementToRemove);
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
