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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaSwitchSetMatch;

import java.io.IOException;
import java.util.Collection;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class JenaTransformationRepairSwitchSet extends JenaTransformationRepair<JenaSwitchSetMatch> {

	public JenaTransformationRepairSwitchSet(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<JenaSwitchSetMatch> matches) throws IOException {
		final Model model = jenaDriver.getModel();
		final Property currentPositionProperty = model.getProperty(BASE_PREFIX + CURRENTPOSITION);

		for (final JenaSwitchSetMatch match : matches) {
			final Resource sw = match.getSw();

			model.remove(sw, currentPositionProperty, match.getCurrentPosition());
			model.add(sw, currentPositionProperty, match.getPosition());
		}
	}

}
