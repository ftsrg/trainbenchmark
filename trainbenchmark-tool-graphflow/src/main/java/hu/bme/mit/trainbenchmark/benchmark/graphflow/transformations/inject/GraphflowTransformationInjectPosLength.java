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
package hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.inject;

import com.google.common.collect.ImmutableMap;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.driver.GraphflowDriver;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.GraphflowTransformation;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class GraphflowTransformationInjectPosLength extends GraphflowTransformation<GraphflowPosLengthInjectMatch> {

	public GraphflowTransformationInjectPosLength(final GraphflowDriver driver, final String workspaceDir) throws IOException {
		super(driver, workspaceDir, RailwayOperation.POSLENGTH_INJECT);
	}

	@Override
	public void activate(final Collection<GraphflowPosLengthInjectMatch> matches) throws IOException {
		for (final GraphflowPosLengthInjectMatch match : matches) {
			final Map<String, Object> parameters = ImmutableMap.of( //
					QueryConstants.VAR_SEGMENT, match.getSegment().getProperty(Neo4jConstants.ID) //
			);
			driver.runTransformation(transformationDefinition, parameters);
		}
	}

}
