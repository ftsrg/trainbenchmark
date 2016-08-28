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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.repair;

import java.util.Collection;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jCoreTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class Neo4jCoreTransformationRepairPosLength extends Neo4jCoreTransformation<Neo4jPosLengthMatch> {

	public Neo4jCoreTransformationRepairPosLength(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Neo4jPosLengthMatch> matches) {
		for (final Neo4jPosLengthMatch plm : matches) {
			final Node segment = plm.getSegment();
			try {
				final Integer length = plm.getLength();
				segment.setProperty(ModelConstants.LENGTH, -length + 1);
			} catch (final NotFoundException e) {
				// do nothing (node has been removed)
			}
		}
	}

}
