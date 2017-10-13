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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.cypher;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.Neo4jQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class Neo4jCypherQuery<TNeo4jMatch extends Neo4jMatch> extends Neo4jQuery<TNeo4jMatch> {
	
	protected final RailwayQuery query;
	protected final String queryDefinition;

	public Neo4jCypherQuery(final Neo4jDriver driver, final String workspaceDir, final RailwayQuery query)
			throws IOException {
		super(query, driver);

		this.query = query;
		this.queryDefinition =
			FileUtils.readFileToString(
				new File(workspaceDir + Neo4jConstants.CYPHER_DIR + "queries/" + query + "." + Neo4jConstants.QUERY_EXTENSION),
				StandardCharsets.UTF_8
			);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TNeo4jMatch> evaluate() throws IOException {
		return (Collection<TNeo4jMatch>) driver.runQuery(query, queryDefinition);
	}

}
