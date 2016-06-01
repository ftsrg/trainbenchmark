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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesamePosLengthMatch;

import java.util.Collection;

import org.openrdf.model.Literal;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

public class SesameTransformationRepairPosLength extends SesameTransformationRepair<SesamePosLengthMatch> {

	public SesameTransformationRepairPosLength(final SesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesamePosLengthMatch> matches) throws RepositoryException {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final URI lengthProperty = vf.createURI(BASE_PREFIX + LENGTH);

		for (final SesamePosLengthMatch match : matches) {
			final Resource segment = match.getSegment();
			final Value length = match.getLength();

			final RepositoryResult<Statement> statementsToRemove = con.getStatements(segment, lengthProperty, length, true);
			while (statementsToRemove.hasNext()) {
				con.remove(statementsToRemove.next());
			}

			final Integer lengthInteger = new Integer(length.stringValue());
			final Integer newLengthInteger = -lengthInteger + 1;
			final Literal newLength = vf.createLiteral(newLengthInteger);
			con.add(segment, lengthProperty, newLength);
		}
	}
}
