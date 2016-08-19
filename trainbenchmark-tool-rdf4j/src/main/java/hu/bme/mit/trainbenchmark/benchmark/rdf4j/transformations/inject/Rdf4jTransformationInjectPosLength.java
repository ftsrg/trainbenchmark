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

import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.Rdf4jTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;

import java.util.Collection;

import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

public class Rdf4jTransformationInjectPosLength extends Rdf4jTransformation<Rdf4jPosLengthInjectMatch> {

	public Rdf4jTransformationInjectPosLength(final Rdf4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Rdf4jPosLengthInjectMatch> matches) {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final IRI typeIri = vf.createIRI(BASE_PREFIX + ModelConstants.LENGTH);
		final Literal zeroLiteral = vf.createLiteral(0);

		for (final Rdf4jPosLengthInjectMatch match : matches) {
			final IRI segment = match.getSegment();

			final RepositoryResult<Statement> statementsToRemove = con.getStatements(segment, typeIri, null, true);
			con.remove(statementsToRemove);

			con.add(segment, typeIri, zeroLiteral);
		}
	}

}
