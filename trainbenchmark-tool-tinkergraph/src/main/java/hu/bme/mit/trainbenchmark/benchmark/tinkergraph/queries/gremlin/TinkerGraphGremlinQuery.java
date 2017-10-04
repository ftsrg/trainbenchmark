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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.gremlin;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.TinkerGraphQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.apache.commons.io.FileUtils;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class TinkerGraphGremlinQuery<TTinkerGraphMatch extends TinkerGraphMatch, TTinkerGraphDriver extends GraphDriver> extends TinkerGraphQuery<TTinkerGraphMatch, TTinkerGraphDriver> {

	protected final RailwayQuery query;
	protected final String queryDefinition;

	public TinkerGraphGremlinQuery(final TTinkerGraphDriver driver, final String workspaceDir, final RailwayQuery query)
		throws IOException {
		super(query, driver);

		this.query = query;
		this.queryDefinition =
			FileUtils.readFileToString(
				new File(workspaceDir + "trainbenchmark-tool-tinkergraph/src/main/resources/queries/" + query + ".gremlin"),
				StandardCharsets.UTF_8
			);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TTinkerGraphMatch> evaluate() throws IOException, ScriptException {
		return (Collection<TTinkerGraphMatch>) driver.runQuery(query, queryDefinition);
	}

}
