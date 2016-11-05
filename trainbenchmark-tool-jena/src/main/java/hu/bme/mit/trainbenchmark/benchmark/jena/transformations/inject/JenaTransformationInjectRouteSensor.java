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

import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

import java.io.IOException;
import java.util.Collection;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;

import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.JenaTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class JenaTransformationInjectRouteSensor extends JenaTransformation<JenaRouteSensorInjectMatch> {

	public JenaTransformationInjectRouteSensor(final JenaDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<JenaRouteSensorInjectMatch> routeSensorInjectMatchsMatches) throws IOException {
		final Model model = driver.getModel();
		final Property requires = model.getProperty(BASE_PREFIX + ModelConstants.REQUIRES);
		
		for (final JenaRouteSensorInjectMatch rsim : routeSensorInjectMatchsMatches) {
			model.remove(rsim.getRoute(), requires, rsim.getSensor()); 
		}
	
	}

}
