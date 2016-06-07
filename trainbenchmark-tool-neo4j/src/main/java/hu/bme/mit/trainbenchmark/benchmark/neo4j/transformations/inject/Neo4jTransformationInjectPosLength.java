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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;

import java.util.Collection;

import org.neo4j.graphdb.Node;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

public class Neo4jTransformationInjectPosLength extends Neo4jTransformationInject {

	public Neo4jTransformationInjectPosLength(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Node> segments) {
		for (final Node segment : segments) {
			segment.setProperty(LENGTH, 0);
		}
	}

}
