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
package hu.bme.mit.trainbenchmark.benchmark.janusgraph.driver;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class JanusGraphDriver extends GraphDriver<Graph> {

	protected JanusGraphDriver() throws IOException {
		super();
	}

	@Override
	public void read(final String modelPath) throws XMLStreamException, IOException, ConfigurationException {
		//final PropertiesConfiguration conf = new PropertiesConfiguration("conf/jgex-berkeleyje.properties");
		final PropertiesConfiguration conf = new PropertiesConfiguration("conf/jgex-inmemory.properties");
		graph = GraphFactory.open(conf);
		graph.io(IoCore.graphml()).readGraph(modelPath);
	}

}
