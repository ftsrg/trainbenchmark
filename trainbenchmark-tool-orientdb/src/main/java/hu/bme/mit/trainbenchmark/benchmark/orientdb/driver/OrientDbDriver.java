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
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.structure.io.graphml.GraphMLReader;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class OrientDbDriver extends TinkerGraphDriver {

	protected final OrientGraph graphDb;

	protected OrientDbDriver() throws IOException {
		super();

		OLogManager.instance().setWarnEnabled(false);
		OLogManager.instance().setInfoEnabled(false);
		OLogManager.instance().setDebugEnabled(false);
		graphDb = new OrientGraph("plocal:" + "/tmp");
	}

	@Override
	public String getPostfix() {
		return "-tinkerpop-orientdb.graphml";
	}

//	public Collection<OrientDbMatch> runQuery(final List<String> lines, RailwayQuery query) throws IOException {
//		OGremlinHelper.global().create();
//		final OCommandGremlin comm = new OCommandGremlin();
//		Object result = null; // sorry (-:
//		for (final String line : lines) {
//			result = comm.setText(line).execute();
//		}
//		System.out.println(result);
//		return null;
//	}


}
