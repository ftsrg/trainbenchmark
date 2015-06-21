/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaRouteSensorMatch;

import java.io.IOException;
import java.util.Collection;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class JenaTransformationRepairRouteSensor extends JenaTransformationRepair<JenaRouteSensorMatch> {

	public JenaTransformationRepairRouteSensor(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<JenaRouteSensorMatch> matches) throws IOException {
		final Model model = jenaDriver.getModel();

		final Property definedBy = model.getProperty(BASE_PREFIX + DEFINED_BY);
		for (final JenaRouteSensorMatch match : matches) {
			final Resource route = match.getRoute();
			final Resource sensor = match.getSensor();

			model.add(model.createStatement(route, definedBy, sensor));
		}
	}

}
