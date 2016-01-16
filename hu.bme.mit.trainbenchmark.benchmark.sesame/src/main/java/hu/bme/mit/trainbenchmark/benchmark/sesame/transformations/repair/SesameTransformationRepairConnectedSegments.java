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
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;

import java.util.Collection;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameConnectedSegmentsMatch;

public class SesameTransformationRepairConnectedSegments extends SesameTransformationRepair<SesameConnectedSegmentsMatch> {

	public SesameTransformationRepairConnectedSegments(final SesameDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<SesameConnectedSegmentsMatch> matches) throws RepositoryException {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final URI connectsTo = vf.createURI(BASE_PREFIX + CONNECTS_TO);
		for (final SesameConnectedSegmentsMatch match : matches) {
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
