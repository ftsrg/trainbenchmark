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
package hu.bme.mit.trainbenchmark.benchmark.iqdcore.benchmarkcases;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hu.bme.mit.incqueryds.ConfigReader;
import hu.bme.mit.incqueryds.WildcardInput;
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkQuery;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDCoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf.checkers.RDFChecker;
import scala.collection.Iterator;
import scala.collection.immutable.Vector;

public class IQDCoreChecker extends RDFChecker<IQDCoreMatch> {

	protected WildcardInput driver;
	protected TrainbenchmarkQuery checker;

	public IQDCoreChecker(final WildcardInput iqdDriver, final IQDCoreBenchmarkConfig iqdbc) throws IOException {
		super(iqdbc, iqdbc.getQuery());
		this.driver = iqdDriver;
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResource(String.format("%s.yaml", iqdbc.getQuery())).openStream();
        this.checker = ConfigReader.parse(iqdbc.getQuery().toString(), inputStream);
        driver.subscribe(checker.inputLookup());

	}

	@Override
	public Collection<IQDCoreMatch> check() throws IOException {
		final List<IQDCoreMatch> matches = new ArrayList<>();

		final Iterator<Vector<Object>> resultIterator = checker.getResults().iterator();
		while (resultIterator.hasNext()) {
			final Vector<Object> qs = resultIterator.next();
			final IQDCoreMatch match = IQDCoreMatch.createMatch(query, qs);
			matches.add(match);
		}
		return matches;
	}

	public TrainbenchmarkQuery getQuery() {
		return checker;
	}
	
	public void shutdown() {
		checker.shutdown();
	}
}
