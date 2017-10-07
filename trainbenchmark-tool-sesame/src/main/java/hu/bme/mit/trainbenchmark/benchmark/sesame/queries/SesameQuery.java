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
package hu.bme.mit.trainbenchmark.benchmark.sesame.queries;

import hu.bme.mit.trainbenchmark.benchmark.rdf.queries.RdfModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.apache.commons.io.FileUtils;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class SesameQuery<TPatternMatch, TSesameDriver extends SesameDriver>
		extends RdfModelQuery<TPatternMatch, TSesameDriver> {

	protected final String queryDefinition;

	public SesameQuery(final TSesameDriver driver, final String workspaceDir, final RailwayQuery query)
			throws IOException {
		super(driver, workspaceDir, query);
		this.queryDefinition = FileUtils.readFileToString(new File(queryPath), StandardCharsets.UTF_8);
	}

	public static <TPatternMatch, TSesameDriver extends SesameDriver> SesameQuery<TPatternMatch, TSesameDriver> create(
			final TSesameDriver driver, final String workspaceDir, final RailwayQuery query) throws IOException {
		return new SesameQuery<TPatternMatch, TSesameDriver>(driver, workspaceDir, query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TPatternMatch> evaluate()
			throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		return (Collection<TPatternMatch>) driver.runQuery(query, queryDefinition);
	}

}
