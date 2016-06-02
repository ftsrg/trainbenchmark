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
package hu.bme.mit.trainbenchmark.benchmark.drools6.checkers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.Message.Level;
import org.kie.api.runtime.rule.LiveQuery;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.drools6.Drools6ResultListener;
import hu.bme.mit.trainbenchmark.benchmark.drools6.driver.Drools6Driver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Drools6ModelQuery extends ModelQuery<EMFMatch, Drools6Driver> {

	protected Collection<EMFMatch> matches = new HashSet<>();
	protected Drools6ResultListener listener;
	protected LiveQuery liveQuery;
	protected RailwayQuery query;

	public Drools6ModelQuery(final BenchmarkConfig benchmarkConfig, final Drools6Driver driver, final RailwayQuery query) throws IOException {
		super(driver);
		this.query = query;
		
		final String queryFile = benchmarkConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.drools6/src/main/resources/queries/" + query + ".drl";
		final File file = new File(queryFile);
		if (!file.exists()) {
			throw new IOException("Query file not found: " + queryFile);
		}
		driver.getKfs().write("src/main/resources/" + query + ".drl", driver.getKieServices().getResources().newFileSystemResource(queryFile));

		final KieBuilder kieBuilder = driver.getKieServices().newKieBuilder(driver.getKfs());
		kieBuilder.buildAll();
		if (kieBuilder.getResults().hasMessages(Level.ERROR)) {
			throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
		}
	}

	@Override
	public Collection<EMFMatch> check() throws IOException {
		if (liveQuery == null) {
			listener = new Drools6ResultListener(query);
			liveQuery = driver.getKsession().openLiveQuery(query.toString(), new Object[] {}, listener);
		} else {
			// activate lazy PHREAK evaluation
			driver.getKsession().fireAllRules();
		}
		matches = listener.getMatches();
		return matches;
	}

	@Override
	public void close() {
		if (liveQuery != null) {
			liveQuery.close();
		}
	}

}
