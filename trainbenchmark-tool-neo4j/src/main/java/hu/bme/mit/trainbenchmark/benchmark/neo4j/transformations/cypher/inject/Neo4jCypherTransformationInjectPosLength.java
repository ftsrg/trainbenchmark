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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.inject;

import com.google.common.collect.ImmutableMap;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jCypherTransformation;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class Neo4jCypherTransformationInjectPosLength extends Neo4jCypherTransformation<Neo4jPosLengthInjectMatch> {

	public Neo4jCypherTransformationInjectPosLength(final Neo4jDriver driver, final String workspaceDir) throws IOException {
		super(driver, workspaceDir, RailwayOperation.POSLENGTH_INJECT);
	}

	@Override
	public void activate(final Collection<Neo4jPosLengthInjectMatch> matches) throws IOException {
		for (final Neo4jPosLengthInjectMatch match : matches) {
			final Map<String, Object> parameters = ImmutableMap.of( //
					QueryConstants.VAR_SEGMENT, match.getSegment() //
			);
			driver.runTransformation(transformationDefinition, parameters);
		}
	}

}
