/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.Rdf4jTransformation;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;

import java.util.Collection;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

public class Rdf4jTransformationRepairConnectedSegments extends Rdf4jTransformation<Rdf4jConnectedSegmentsMatch> {

	public Rdf4jTransformationRepairConnectedSegments(final Rdf4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Rdf4jConnectedSegmentsMatch> matches) {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final IRI connectsTo = vf.createIRI(BASE_PREFIX + CONNECTS_TO);
		for (final Rdf4jConnectedSegmentsMatch match : matches) {
			// delete segment2 by removing all (segment2, _, _) and (_, _, segment2) triples
			final RepositoryResult<Statement> outgoingEdges = con.getStatements(match.getSegment2(), null, null, true);
			while (outgoingEdges.hasNext()) {
				con.remove(outgoingEdges.next());
			}
			final RepositoryResult<Statement> incomingEdges = con.getStatements(null, null, match.getSegment2(), true);
			while (incomingEdges.hasNext()) {
				con.remove(incomingEdges.next());
			}

			// insert (segment1)-[:connectsTo]->(segment3) edge
			con.add(match.getSegment1(), connectsTo, match.getSegment3());
		}
	}
}
