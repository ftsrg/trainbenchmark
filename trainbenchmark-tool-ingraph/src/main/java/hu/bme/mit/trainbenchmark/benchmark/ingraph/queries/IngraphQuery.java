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
package hu.bme.mit.trainbenchmark.benchmark.ingraph.queries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkQuery;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf.queries.RdfModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import scala.collection.Iterator;
import scala.collection.immutable.Vector;

public class IngraphQuery<TPatternMatch extends IngraphMatch> extends RdfModelQuery<TPatternMatch, IngraphDriver> {

	private final TrainbenchmarkQuery queryEngine;
	
	public IngraphQuery(final IngraphDriver driver, final String workspaceDir, final RailwayQuery query, final TransactionFactory input)
			throws IOException {
		super(driver, workspaceDir, query);
		queryEngine = null;
//		queryEngine = ConfigReader.parse(query.name(), new FileInputStream(yamlPath), false);
//		input.subscribe(queryEngine.inputLookup());
//		driver.setQuery(queryEngine);
	}
	
	public static <TPatternMatch extends IngraphMatch> IngraphQuery<TPatternMatch>
		create(final IngraphDriver driver, final String queryDirectory, final RailwayQuery query, final TransactionFactory input)
			throws IOException {
		return new IngraphQuery<TPatternMatch>(driver, queryDirectory, query, input);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TPatternMatch> evaluate() {
		driver.flushLastTransaction();
		driver.maybeMeasureMemory();

		final List<IngraphMatch> matches = new ArrayList<>();

		final Iterator<Vector<Object>> resultIterator = queryEngine.getResults().iterator();
		while (resultIterator.hasNext()) {
			final Vector<Object> qs = resultIterator.next();
			final IngraphMatch match = IngraphMatch.createMatch(query, qs);
			matches.add(match);
		}
		return (Collection<TPatternMatch>) matches;

	}

}
