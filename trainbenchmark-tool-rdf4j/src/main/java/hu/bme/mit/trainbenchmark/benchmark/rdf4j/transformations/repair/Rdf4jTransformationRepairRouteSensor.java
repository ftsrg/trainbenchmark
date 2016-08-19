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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.GATHERS;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

import java.util.Collection;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;

import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.Rdf4jTransformation;

public class Rdf4jTransformationRepairRouteSensor<TRdf4jDriver extends Rdf4jDriver> extends Rdf4jTransformation<Rdf4jRouteSensorMatch, TRdf4jDriver> {

	public Rdf4jTransformationRepairRouteSensor(final TRdf4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Rdf4jRouteSensorMatch> matches) {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final IRI gathers = vf.createIRI(BASE_PREFIX + GATHERS);

		for (final Rdf4jRouteSensorMatch match : matches) {
			final Resource route = match.getRoute();
			final Resource sensor = match.getSensor();

			con.add(route, gathers, sensor);
		}
	}

}
