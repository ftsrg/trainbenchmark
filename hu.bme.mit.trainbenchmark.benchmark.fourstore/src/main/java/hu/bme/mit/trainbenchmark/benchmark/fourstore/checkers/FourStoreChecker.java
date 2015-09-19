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

package hu.bme.mit.trainbenchmark.benchmark.fourstore.checkers;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.driver.FourStoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.openrdf.query.BindingSet;

public class FourStoreChecker extends Checker<SesameMatch> {

	protected final FourStoreDriver driver;
	protected final Query query;
	protected String queryDefinition;

	public FourStoreChecker(final FourStoreDriver driver, final FourStoreBenchmarkConfig fsbc)
			throws IOException {
		super();
		this.driver = driver;
		this.query = fsbc.getQuery();

		final String queryPath = fsbc.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/"
				+ fsbc.getQuery() + ".sparql";
		this.queryDefinition = FileUtils.readFileToString(new File(queryPath));
	}

	@Override
	public Collection<SesameMatch> check() throws Exception {
		final Collection<SesameMatch> matches = new LinkedList<>();
		final Collection<BindingSet> bindingSets = driver.runQuery(query, queryDefinition);
		for (final BindingSet bindingSet : bindingSets) {
			final SesameMatch match = SesameMatch.createMatch(query, bindingSet);
			matches.add(match);
		}
		return matches;
	}

	public String getQueryDefinition() {
		return queryDefinition;
	}

	public void setQueryDefinition(String queryDefinition) {
		this.queryDefinition = queryDefinition;
	}

}
