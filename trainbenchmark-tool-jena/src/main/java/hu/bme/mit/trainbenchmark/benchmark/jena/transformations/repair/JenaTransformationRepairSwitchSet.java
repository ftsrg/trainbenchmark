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
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.JenaTransformation;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import java.io.IOException;
import java.util.Collection;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

public class JenaTransformationRepairSwitchSet extends JenaTransformation<JenaSwitchSetMatch> {

	public JenaTransformationRepairSwitchSet(final JenaDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<JenaSwitchSetMatch> matches) throws IOException {
		final Model model = driver.getModel();
		final Property currentPositionProperty = model.getProperty(BASE_PREFIX + CURRENTPOSITION);

		for (final JenaSwitchSetMatch match : matches) {
			final Resource sw = match.getSw();

			model.remove(sw, currentPositionProperty, match.getCurrentPosition());
			model.add(sw, currentPositionProperty, match.getPosition());
		}
	}

}
