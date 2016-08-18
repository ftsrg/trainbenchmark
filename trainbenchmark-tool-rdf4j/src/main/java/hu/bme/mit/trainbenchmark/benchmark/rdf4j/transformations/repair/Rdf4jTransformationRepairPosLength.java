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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

import java.util.Collection;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.RepositoryResult;

import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.Rdf4jTransformation;

public class Rdf4jTransformationRepairPosLength extends Rdf4jTransformation<Rdf4jPosLengthMatch> {

	public Rdf4jTransformationRepairPosLength(final Rdf4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Rdf4jPosLengthMatch> matches) throws RepositoryException {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final IRI lengthProperty = vf.createIRI(BASE_PREFIX + LENGTH);

		for (final Rdf4jPosLengthMatch match : matches) {
			final Resource segment = match.getSegment();
			final Value length = match.getLength();

			final RepositoryResult<Statement> statementsToRemove = con.getStatements(segment, lengthProperty, length, true);
			while (statementsToRemove.hasNext()) {
				final Statement oldStatement = statementsToRemove.next();
				con.remove(oldStatement);
			}

			final Integer lengthInteger = new Integer(length.stringValue());
			final Integer newLengthInteger = -lengthInteger + 1;
			final Literal newLength = vf.createLiteral(newLengthInteger);
			final Statement newStatement = vf.createStatement(segment, lengthProperty, newLength);
			con.add(newStatement);
		}
	}
}
