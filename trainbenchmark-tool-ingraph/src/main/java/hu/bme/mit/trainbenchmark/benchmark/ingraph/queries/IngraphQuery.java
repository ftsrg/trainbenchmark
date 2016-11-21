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

import java.util.Collection;

import hu.bme.mit.ire.TransactionFactory;
import hu.bme.mit.ire.trainbenchmark.TrainbenchmarkQuery;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.IngraphUtils;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import ingraph.ire.IngraphAdapter;
import relalg.RelalgContainer;

public class IngraphQuery<TPatternMatch extends IngraphMatch> extends ModelQuery<TPatternMatch, IngraphDriver> {

	private TrainbenchmarkQuery queryEngine;
	private IngraphChangeListener listener;

	public IngraphQuery(final IngraphDriver driver, final RailwayQuery query, final TransactionFactory input) throws Exception {
		super(query, driver);
		final RelalgContainer plan = IngraphUtils.getQueryPlan(query.toString(), driver.getQueryVariant());
		final IngraphAdapter adapter = new IngraphAdapter(plan);
		queryEngine = adapter.engine();
		listener = new IngraphChangeListener(query);
		queryEngine.addListener(listener);
		input.subscribe(queryEngine.inputLookup());
		driver.setAdapter(adapter);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TPatternMatch> evaluate() {
		return (Collection<TPatternMatch>) listener.results;
	}

}
