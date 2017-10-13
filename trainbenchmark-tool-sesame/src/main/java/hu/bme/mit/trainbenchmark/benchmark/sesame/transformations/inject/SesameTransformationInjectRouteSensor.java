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

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

public class SesameTransformationInjectRouteSensor<TSesameDriver extends SesameDriver> extends SesameTransformation<SesameRouteSensorInjectMatch, TSesameDriver> {

	public SesameTransformationInjectRouteSensor(final TSesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesameRouteSensorInjectMatch> matches) throws RepositoryException {
		final RepositoryConnection connection = driver.getConnection();
		final ValueFactory vf = connection.getValueFactory();

		final List<Statement> statementsToRemove = new ArrayList<>(matches.size());
		final URI requires = vf.createURI(BASE_PREFIX + ModelConstants.REQUIRES);
		for (final SesameRouteSensorInjectMatch match : matches) {
			final Statement statement = vf.createStatement(match.getRoute(), requires, match.getSensor());
			statementsToRemove.add(statement);
		}
		connection.remove(statementsToRemove);
	}

}
