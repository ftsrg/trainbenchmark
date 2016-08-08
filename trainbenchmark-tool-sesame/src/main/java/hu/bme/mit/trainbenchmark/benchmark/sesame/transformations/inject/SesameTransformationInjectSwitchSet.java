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
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameVertexMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;
import hu.bme.mit.trainbenchmark.constants.Position;
import hu.bme.mit.trainbenchmark.rdf.RdfHelper;

public class SesameTransformationInjectSwitchSet extends SesameTransformation<SesameVertexMatch> {

	public SesameTransformationInjectSwitchSet(final SesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesameVertexMatch> switchMatches) throws RepositoryException {
		final List<URI> switches = switchMatches.stream().map(it -> it.getVertex()).collect(Collectors.toList());

		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final URI currentPositionProperty = vf.createURI(BASE_PREFIX + CURRENTPOSITION);

		for (final URI sw : switches) {
			final RepositoryResult<Statement> statements = con.getStatements(sw, currentPositionProperty, null, true);
			if (!statements.hasNext()) {
				continue;
			}

			final Statement oldStatement = statements.next();

			// delete old statement
			con.remove(oldStatement);

			// get next enum value
			final URI currentPositionURI = (URI) oldStatement.getObject();
			final String currentPositionRDFString = currentPositionURI.getLocalName();
			final String currentPositionString = RdfHelper.removePrefix(Position.class, currentPositionRDFString);
			final Position currentPosition = Position.valueOf(currentPositionString);
			final Position newCurrentPosition = Position.values()[(currentPosition.ordinal() + 1) % Position.values().length];
			final String newCurrentPositionString = RdfHelper.addEnumPrefix(newCurrentPosition);
			final URI newCurrentPositionUri = vf.createURI(BASE_PREFIX + newCurrentPositionString);

			// set new value
			con.add(sw, currentPositionProperty, newCurrentPositionUri);
		}
	}

}
