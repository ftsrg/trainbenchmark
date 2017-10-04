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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.driver;

import com.orientechnologies.common.log.OLogManager;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;

import java.io.IOException;

public class OrientDbDriver extends GraphDriver<OrientGraph> {

	protected OrientDbDriver() throws IOException {
		super();

		OLogManager.instance().setWarnEnabled(false);
		OLogManager.instance().setInfoEnabled(false);
		OLogManager.instance().setDebugEnabled(false);
		graph = OrientGraph.open();
	}

}
