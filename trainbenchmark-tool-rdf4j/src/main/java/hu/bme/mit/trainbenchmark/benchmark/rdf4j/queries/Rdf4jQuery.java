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
package hu.bme.mit.trainbenchmark.benchmark.rdf4j.queries;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import org.apache.commons.io.FileUtils;

import hu.bme.mit.trainbenchmark.benchmark.rdf.queries.RdfModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Rdf4jQuery<TPatternMatch> extends RdfModelQuery<TPatternMatch, Rdf4jDriver> {

	protected final String queryDefinition;

	public Rdf4jQuery(final Rdf4jDriver driver, final Optional<String> workspaceDir, final RailwayQuery query) throws IOException {
		super(driver, workspaceDir, query);
		this.queryDefinition = FileUtils.readFileToString(new File(queryPath));
	}

	public static <TPatternMatch> Rdf4jQuery<TPatternMatch> create(final Rdf4jDriver driver,
			final Optional<String> workspaceDir, final RailwayQuery query) throws IOException {
		return new Rdf4jQuery<TPatternMatch>(driver, workspaceDir, query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TPatternMatch> evaluate() {
		return (Collection<TPatternMatch>) driver.runQuery(query, queryDefinition);
	}

}
