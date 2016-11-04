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

import java.io.IOException;
import java.util.Collection;

import org.kie.api.io.ResourceType;
import org.kie.api.runtime.rule.LiveQuery;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import hu.bme.mit.trainbenchmark.benchmark.drools.driver.DroolsDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class DroolsQuery<TMatch extends EmfMatch> extends ModelQuery<TMatch, DroolsDriver> {

	protected Collection<TMatch> matches;
	protected DroolsResultListener listener;
	protected LiveQuery liveQuery;

	protected DroolsQuery(final DroolsDriver driver, final String workspaceDir, final RailwayQuery query)
			throws IOException {
		super(query, driver);

		final String queryFile = query + ".drl";
		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(queryFile, DroolsQuery.class), ResourceType.DRL);
	}

	public static <TMatch extends EmfMatch> DroolsQuery<TMatch> create(final DroolsDriver driver,
			final String workspaceDir, final RailwayQuery query) throws IOException {
		return new DroolsQuery<TMatch>(driver, workspaceDir, query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TMatch> evaluate() throws IOException {
		if (liveQuery == null) {
			listener = new DroolsResultListener(query);
			liveQuery = driver.getKsession().openLiveQuery(query.toString(), new Object[] {}, listener);
		} else {
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
