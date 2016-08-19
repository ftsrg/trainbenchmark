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
package hu.bme.mit.trainbenchmark.benchmark.drools.queries;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.Message.Level;
import org.kie.api.runtime.rule.LiveQuery;

import hu.bme.mit.trainbenchmark.benchmark.drools.DroolsResultListener;
import hu.bme.mit.trainbenchmark.benchmark.drools.driver.DroolsDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class DroolsQuery<TMatch extends EmfMatch> extends ModelQuery<TMatch, DroolsDriver> {

	protected Collection<TMatch> matches;
	protected DroolsResultListener listener;
	protected LiveQuery liveQuery;

	protected DroolsQuery(final DroolsDriver driver, final Optional<String> workspaceDir,
			final RailwayQuery query) throws IOException {
		super(query, driver);

		final String queryFile = workspaceDir.get()
				+ "/trainbenchmark-tool-drools/src/main/resources/queries/" + query + ".drl";
		final File file = new File(queryFile);
		if (!file.exists()) {
			throw new IOException("Query file not found: " + queryFile);
		}
		driver.getKfs().write("src/main/resources/" + query + ".drl",
				driver.getKieServices().getResources().newFileSystemResource(queryFile));

		final KieBuilder kieBuilder = driver.getKieServices().newKieBuilder(driver.getKfs());
		kieBuilder.buildAll();
		if (kieBuilder.getResults().hasMessages(Level.ERROR)) {
			throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
		}
	}

	public static <TMatch extends EmfMatch> DroolsQuery<TMatch> create(final DroolsDriver driver,
			final Optional<String> workspaceDir, final RailwayQuery query) throws IOException {
		return new DroolsQuery<TMatch>(driver, workspaceDir, query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TMatch> evaluate() throws IOException {
		if (liveQuery == null) {
			listener = new DroolsResultListener(query);
			liveQuery = driver.getKsession().openLiveQuery(query.toString(), new Object[] {}, listener);
		} else {
			// activate lazy PHREAK evaluation
			driver.getKsession().fireAllRules();
		}
		matches = (Collection<TMatch>) listener.getMatches();
		return matches;
	}

	@Override
	public void close() {
		if (liveQuery != null) {
			liveQuery.close();
		}
	}

}
