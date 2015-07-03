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
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.constants.Position;
import hu.bme.mit.trainbenchmark.rdf.RDFHelper;

import java.util.Collection;

import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

public class SesameTransformationInjectSwitchSet extends SesameTransformationInject {

	public SesameTransformationInjectSwitchSet(final SesameDriver sesameDriver) {
		super(sesameDriver);
	}

	@Override
	public void rhs(final Collection<URI> switches) throws RepositoryException {
		final RepositoryConnection con = sesameDriver.getConnection();
		final ValueFactory vf = sesameDriver.getValueFactory();

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
			final URI object = (URI) oldStatement.getObject();
			final String currentPositionRDFString = object.getLocalName();
			final String currentPositionString = RDFHelper.removePrefix(Position.class, currentPositionRDFString);
			final Position currentPosition = Position.valueOf(currentPositionString);
			final Position newCurrentPosition = Position.values()[(currentPosition.ordinal() + 1) % Position.values().length];
			final String newCurrentPositionString = RDFHelper.addEnumPrefix(newCurrentPosition);
			final Literal newCurrentPositionLiteral = vf.createLiteral(newCurrentPositionString);

			// set new value
			con.add(sw, currentPositionProperty, newCurrentPositionLiteral);
		}
	}

}
