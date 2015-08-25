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

import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants;

import java.util.Collection;

import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

public class SesameTransformationInjectPosLength extends SesameTransformationInject {

	public SesameTransformationInjectPosLength(final SesameDriver sesameDriver) {
		super(sesameDriver);
	}

	@Override
	public void rhs(final Collection<URI> segments) throws RepositoryException {
		final RepositoryConnection con = sesameDriver.getConnection();
		final ValueFactory vf = sesameDriver.getValueFactory();

		final URI typeURI = vf.createURI(BASE_PREFIX + RailwayModelConstants.LENGTH);
		final Literal zeroLiteral = vf.createLiteral(0);

		for (final URI segment : segments) {
			final RepositoryResult<Statement> statementsToRemove = con.getStatements(segment, typeURI, null, true);
			while (statementsToRemove.hasNext()) {
				con.remove(statementsToRemove.next());
			}

			con.add(segment, typeURI, zeroLiteral);
		}
	}

}
