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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;

import java.io.IOException;
import java.util.Collection;

import org.apache.jena.rdf.model.Resource;

import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;

public class JenaTransformationInjectSwitchSensor extends JenaTransformationInject {

	public JenaTransformationInjectSwitchSensor(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void activate(final Collection<Resource> switches) throws IOException {
		driver.deleteSingleOutgoingEdge(switches, SWITCH, MONITORED_BY);
	}

}
