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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import hu.bme.mit.incqueryds.ConfigReader;
import hu.bme.mit.incqueryds.WildcardInput;
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkQuery;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf.queries.RdfModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import scala.collection.Iterator;
import scala.collection.immutable.Vector;

public class IQDCoreQuery<TPatternMatch extends IQDCoreMatch> extends RdfModelQuery<TPatternMatch, IQDCoreDriver> {

	private final TrainbenchmarkQuery queryEngine;

	public IQDCoreQuery(final IQDCoreDriver driver, final String queryDirectory, final RailwayQuery query, WildcardInput input)
			throws IOException {
		super(driver, Optional.of(queryDirectory), query);
		final String yamlPath = queryDirectory + query + ".yaml";
		queryEngine = ConfigReader.parse(query.name(), new FileInputStream(yamlPath));
		input.subscribe(queryEngine.inputLookup());
	}
	
	public static <TPatternMatch extends IQDCoreMatch> IQDCoreQuery<TPatternMatch> create(final IQDCoreDriver driver, final String queryDirectory, final RailwayQuery query, final WildcardInput input)
			throws IOException {
		return new IQDCoreQuery<TPatternMatch>(driver, queryDirectory, query, input);
	}

	@Override
	public Collection<TPatternMatch> evaluate() {
		final List<IQDCoreMatch> matches = new ArrayList<>();

		final Iterator<Vector<Object>> resultIterator = queryEngine.getResults().iterator();
		while (resultIterator.hasNext()) {
			final Vector<Object> qs = resultIterator.next();
			final IQDCoreMatch match = IQDCoreMatch.createMatch(query, qs);
			matches.add(match);
		}
		return (Collection<TPatternMatch>) matches;
	}

}
