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
package hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

import java.util.Collection;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;

import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.Rdf4jTransformation;
import hu.bme.mit.trainbenchmark.constants.Position;
import hu.bme.mit.trainbenchmark.rdf.RdfHelper;

public class Rdf4jTransformationInjectSwitchSet<TRdf4jDriver extends Rdf4jDriver> extends Rdf4jTransformation<Rdf4jSwitchSetInjectMatch, TRdf4jDriver> {

	public Rdf4jTransformationInjectSwitchSet(final TRdf4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Rdf4jSwitchSetInjectMatch> matches) {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final IRI currentPositionProperty = vf.createIRI(BASE_PREFIX + CURRENTPOSITION);

		for (final Rdf4jSwitchSetInjectMatch match : matches) {
			final IRI sw = match.getSw();
			final RepositoryResult<Statement> statements = con.getStatements(sw, currentPositionProperty, null, true);
			if (!statements.hasNext()) {
				continue;
			}

			final Statement oldStatement = statements.next();

			// delete old statement
			con.remove(oldStatement);

			// get next enum value
			final IRI currentPositionIri = (IRI) oldStatement.getObject();
			final String currentPositionRDFString = currentPositionIri.getLocalName();
			final String currentPositionString = RdfHelper.removePrefix(Position.class, currentPositionRDFString);
			final Position currentPosition = Position.valueOf(currentPositionString);
			final Position newCurrentPosition = Position.values()[(currentPosition.ordinal() + 1) % Position.values().length];
			final String newCurrentPositionString = RdfHelper.addEnumPrefix(newCurrentPosition);
			final IRI newCurrentPositionIri = vf.createIRI(BASE_PREFIX + newCurrentPositionString);

			// set new value
			con.add(sw, currentPositionProperty, newCurrentPositionIri);
		}
	}

}
