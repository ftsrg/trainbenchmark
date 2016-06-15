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

import hu.bme.mit.incqueryds.ConfigReader;
import hu.bme.mit.incqueryds.WildcardInput;
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkQuery;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf.queries.RdfModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public class IQDCoreQuery<TPatternMatch extends IQDCoreMatch> extends RdfModelQuery<TPatternMatch, IQDCoreDriver> {

	private final TrainbenchmarkQuery queryEngine;

	public IQDCoreQuery(final IQDCoreDriver driver, final Optional<String> queryDirectory, final RailwayQuery query, WildcardInput input)
			throws IOException {
		super(driver, queryDirectory, query);
		queryEngine = ConfigReader.parse(query.name(), new FileInputStream(queryPath));
		input.subscribe(queryEngine.inputLookup());
	}
	
	public static <TPatternMatch extends IQDCoreMatch> IQDCoreQuery<TPatternMatch> create(final IQDCoreDriver driver, final Optional<String> queryDirectory, final RailwayQuery query, final WildcardInput input)
			throws IOException {
		return new IQDCoreQuery<TPatternMatch>(driver, queryDirectory, query, input);
	}

	@Override
	public Collection<TPatternMatch> evaluate() {
		return (Collection<TPatternMatch>) queryEngine.getResults();
	}

}
