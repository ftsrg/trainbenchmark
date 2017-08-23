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
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.GraphflowTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.Position;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class GraphflowTransformationInjectSwitchSet extends GraphflowTransformation<GraphflowSwitchSetInjectMatch> {

	public GraphflowTransformationInjectSwitchSet(final GraphflowDriver driver, final String workspaceDir) throws IOException {
		super(driver, workspaceDir, RailwayOperation.SWITCHSET_INJECT);
	}

	@Override
	public void activate(final Collection<GraphflowSwitchSetInjectMatch> matches) throws IOException {
		for (final GraphflowSwitchSetInjectMatch match : matches) {
			final String currentPositionString = (String) match.getSw().getProperty(ModelConstants.CURRENTPOSITION);
			final Position currentPosition = Position.valueOf(currentPositionString);
			final Position newCurrentPosition = Position.values()[(currentPosition.ordinal() + 1) % Position.values().length];

			final Map<String, Object> parameters = ImmutableMap.of( //
					QueryConstants.VAR_SW, match.getSw().getProperty(Neo4jConstants.ID), //
					QueryConstants.VAR_CURRENTPOSITION, newCurrentPosition.toString()
			);
			driver.runTransformation(transformationDefinition, parameters);
		}
	}

}
