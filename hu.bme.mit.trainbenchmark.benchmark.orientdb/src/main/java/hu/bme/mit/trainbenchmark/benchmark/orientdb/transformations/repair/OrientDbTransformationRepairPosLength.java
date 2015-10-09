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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbPosLengthMatch;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationRepairPosLength extends OrientDbTransformationRepair<OrientDbPosLengthMatch> {

	public OrientDbTransformationRepairPosLength(final OrientDbDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<OrientDbPosLengthMatch> matches) {
		for (final OrientDbPosLengthMatch plm : matches) {
			final Vertex segment = plm.getSegment();
			final Integer length = (Integer) segment.getProperty(LENGTH);
			segment.setProperty(LENGTH, -length + 1);
		}
	}
	
}
