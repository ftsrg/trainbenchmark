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

import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

import java.util.Collection;

import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameVertexMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class SesameTransformationInjectPosLength extends SesameTransformationInject {

	public SesameTransformationInjectPosLength(final SesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesameVertexMatch> segments) throws RepositoryException {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final URI typeURI = vf.createURI(BASE_PREFIX + ModelConstants.LENGTH);
		final Literal zeroLiteral = vf.createLiteral(0);

		for (final SesameVertexMatch segmentMatch : segments) {
			final URI segment = segmentMatch.getVertex();

			final RepositoryResult<Statement> statementsToRemove = con.getStatements(segment, typeURI, null, true);
			while (statementsToRemove.hasNext()) {
				con.remove(statementsToRemove.next());
			}

			con.add(segment, typeURI, zeroLiteral);
		}
	}

}
