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
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject;

import java.io.IOException;
import java.util.Collection;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;

import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.JenaTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RdfConstants;

public class JenaTransformationInjectSemaphoreNeighbor extends JenaTransformation<JenaSemaphoreNeighborInjectMatch> {

	public JenaTransformationInjectSemaphoreNeighbor(final JenaDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<JenaSemaphoreNeighborInjectMatch> matches) throws IOException {
		final Model model = driver.getModel();
		final Property entry = model.getProperty(RdfConstants.BASE_PREFIX + ModelConstants.ENTRY);
		for (JenaSemaphoreNeighborInjectMatch match : matches) {
			model.remove(match.getRoute(), entry, match.getSemaphore());
		}
	}

}
