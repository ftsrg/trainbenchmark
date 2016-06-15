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
package hu.bme.mit.trainbenchmark.benchmark.iqdcore.queries;

import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf.queries.RdfModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.apache.commons.io.FileUtils;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public class IQDCoreQuery<TPatternMatch extends IQDCoreMatch> extends RdfModelQuery<TPatternMatch, IQDCoreDriver> {

	protected final String queryDefinition;

	public IQDCoreQuery(final IQDCoreDriver driver, final Optional<String> queryDirectory, final RailwayQuery query)
			throws IOException {
		super(driver, queryDirectory, query);
		this.queryDefinition = FileUtils.readFileToString(new File(queryPath));
	}
	
	public static <TPatternMatch extends IQDCoreMatch> IQDCoreQuery<TPatternMatch> create(final IQDCoreDriver driver, final Optional<String> queryDirectory, final RailwayQuery query)
			throws IOException {
		return new IQDCoreQuery<TPatternMatch>(driver, queryDirectory, query);
	}

	@Override
	public Collection<TPatternMatch> evaluate() {
		return (Collection<TPatternMatch>) driver.runQuery(query, queryDefinition);
	}

}
