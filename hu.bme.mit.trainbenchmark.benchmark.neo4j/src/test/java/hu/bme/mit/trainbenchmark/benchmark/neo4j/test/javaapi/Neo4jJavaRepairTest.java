/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.neo4j.test.javaapi;

import hu.bme.mit.trainbenchmark.benchmark.test.RepairTest;

public class Neo4jJavaRepairTest extends RepairTest {

	public Neo4jJavaRepairTest() {
		bi = new Neo4jJavaBenchmarkInitializer();
	}

}
