/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.checker;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public abstract class EMFIncQueryChecker<M extends BasePatternMatch> extends Checker<M> {

	protected Collection<M> matches;
	protected final EMFIncQueryDriver<M> eiqDriver;
	protected final EMFIncQueryBenchmarkConfig eiqbc;

	protected EMFIncQueryChecker(final EMFIncQueryBenchmarkConfig eiqbc, final EMFIncQueryDriver<M> eiqDriver) {
		this.eiqbc = eiqbc;
		this.eiqDriver = eiqDriver;
	}

	public static EMFIncQueryChecker<?> newInstance(final EMFIncQueryBenchmarkConfig eiqbc, final EMFIncQueryDriver eiqDriver, final Query query) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new EMFIncQueryConnectedSegmentsChecker(eiqbc, eiqDriver);
		case POSLENGTH:
			return new EMFIncQueryPosLengthChecker(eiqbc, eiqDriver);
		case ROUTESENSOR:
			return new EMFIncQueryRouteSensorChecker(eiqbc, eiqDriver);
		case SEMAPHORENEIGHBOR:
			return new EMFIncQuerySemaphoreNeighborChecker(eiqbc, eiqDriver);
		case SWITCHSENSOR:
			return new EMFIncQuerySwitchSensorChecker(eiqbc, eiqDriver);
		case SWITCHSET:
			return new EMFIncQuerySwitchSetChecker(eiqbc, eiqDriver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

	@Override
	public Collection<M> check() throws IOException {
		if (eiqbc.isLocalSearch()) {
			try {
				matches = new ArrayList<>();
				for (final M match : getMatcher().getAllMatches()) {
					matches.add(match);
				}
			} catch (final IncQueryException e) {
				throw new IOException(e);
			}
		}
		return matches;
	}

	public void setMatches(final Collection<M> matches) {
		this.matches = matches;
	}

	public abstract IncQueryMatcher<M> getMatcher() throws IncQueryException;

}
