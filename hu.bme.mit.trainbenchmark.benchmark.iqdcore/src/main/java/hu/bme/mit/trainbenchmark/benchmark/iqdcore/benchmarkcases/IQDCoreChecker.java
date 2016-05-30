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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hu.bme.mit.incquerydcore.WildcardInput;
import hu.bme.mit.incquerydcore.trainbenchmark.*;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDCoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.rdf.checkers.RDFChecker;
import scala.collection.Iterator;
import scala.collection.immutable.Vector;

public class IQDCoreChecker extends RDFChecker<IQDCoreMatch> {

	protected WildcardInput iqdInput;
	protected TrainbenchmarkQuery checker;

	public IQDCoreChecker(final WildcardInput iqdDriver, final IQDCoreBenchmarkConfig iqdbc) throws IOException {
		super(iqdbc);
		this.iqdInput = iqdDriver;
		ClassLoader classLoader = IQDCoreChecker.class.getClassLoader();
		try {
			String classname = String.format("hu.bme.mit.incquerydcore.trainbenchmark.%s%s",  iqdbc.getChecker(), iqdbc.getQuery());
			this.checker = (TrainbenchmarkQuery) classLoader.loadClass(classname).newInstance();
			iqdInput.subscribe(checker.inputLookup());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
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
