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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.user;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.util.Collection;

import org.neo4j.graphdb.Node;

public class Neo4jTransformationUserSwitchSensor extends Neo4jTransformationUser<Neo4jPosLengthMatch> {

	public Neo4jTransformationUserSwitchSensor() {
		super();
	}

	@Override
	public void rhs(final Collection<Neo4jPosLengthMatch> matches) {
		for (final Neo4jPosLengthMatch plm : matches) {
			final Node segment = plm.getSegment();
			final Integer length = (Integer) segment.getProperty(ModelConstants.LENGTH);
			segment.setProperty(LENGTH, -length + 1);
		}
	}

}
