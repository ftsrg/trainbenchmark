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

import hu.bme.mit.ire.TransactionFactory;
import hu.bme.mit.ire.trainbenchmark.TrainbenchmarkQuery;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.IngraphUtils;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import ingraph.ire.IngraphAdapter;
import relalg.RelalgContainer;
import scala.collection.Iterator;
import scala.collection.immutable.Map;

public class IngraphQuery<TPatternMatch extends IngraphMatch> extends ModelQuery<TPatternMatch, IngraphDriver> {

	private TrainbenchmarkQuery queryEngine;
	
	public IngraphQuery(final IngraphDriver driver, final RailwayQuery query, final TransactionFactory input)
			throws IOException {
		super(query, driver);
		try {
			final RelalgContainer plan = IngraphUtils.getQueryPlan(query.toString(), driver.getQueryVariant());
			final IngraphAdapter adapter = new IngraphAdapter(plan);
			queryEngine = adapter.engine();
			input.subscribe(queryEngine.inputLookup());
			driver.setAdapter(adapter);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TPatternMatch> evaluate() {
		driver.flushLastTransaction();
		driver.maybeMeasureMemory();

		final List<IngraphMatch> matches = new ArrayList<>();

		final Iterator<Map<Object, Object>> resultIterator = queryEngine.getResults().iterator();
		while (resultIterator.hasNext()) {
			final Map<Object, Object> qs = resultIterator.next();
			final IngraphMatch match = IngraphMatch.createMatch(query, qs);
			matches.add(match);
		}
		return (Collection<TPatternMatch>) matches;
	}

}
