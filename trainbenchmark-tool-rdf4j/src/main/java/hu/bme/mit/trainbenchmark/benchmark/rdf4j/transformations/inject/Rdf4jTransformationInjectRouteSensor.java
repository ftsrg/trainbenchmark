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

import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;

import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.Rdf4jTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class Rdf4jTransformationInjectRouteSensor<TRdf4jDriver extends Rdf4jDriver> extends Rdf4jTransformation<Rdf4jRouteSensorInjectMatch, TRdf4jDriver> {

	public Rdf4jTransformationInjectRouteSensor(final TRdf4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Rdf4jRouteSensorInjectMatch> matches) {
		final RepositoryConnection connection = driver.getConnection();
		final ValueFactory vf = connection.getValueFactory();
		
		final List<Statement> statementsToRemove = new ArrayList<>(matches.size());
		final IRI gathers = vf.createIRI(BASE_PREFIX + ModelConstants.GATHERS);				
		for (final Rdf4jRouteSensorInjectMatch m : matches) {
			final Statement statement = vf.createStatement(m.getRoute(), gathers, m.getSensor());
			statementsToRemove.add(statement);
		}
		connection.remove(statementsToRemove);
	}

}
