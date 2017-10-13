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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.repair;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jApiTransformation;
import org.neo4j.graphdb.Node;
import org.neo4j.kernel.api.exceptions.KernelException;

import java.util.Collection;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;

public class Neo4jApiTransformationRepairSwitchSet extends Neo4jApiTransformation<Neo4jSwitchSetMatch> {

	public Neo4jApiTransformationRepairSwitchSet(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Neo4jSwitchSetMatch> matches) throws KernelException {
		for (final Neo4jSwitchSetMatch match : matches) {
			final Node sw = match.getSw();
			final String position = match.getPosition();
			sw.setProperty(CURRENTPOSITION, position);
		}
	}

}
