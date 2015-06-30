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
package hu.bme.mit.trainbenchmark.benchmark.drools5.checkers;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.drools5.Drools5ResultListener;
import hu.bme.mit.trainbenchmark.benchmark.drools5.driver.Drools5Driver;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import org.drools.runtime.rule.LiveQuery;

public class Drools5Checker extends Checker<EMFMatch> {

	protected final Drools5Driver driver;
	protected Collection<EMFMatch> matches = new HashSet<>();
	protected Drools5ResultListener listener;
	protected LiveQuery liveQuery;
	protected Query query;

	public Drools5Checker(final Drools5Driver driver, final Query query) {
		super();
		this.driver = driver;
		this.query = query;
	}

	@Override
	public Collection<EMFMatch> check() throws IOException {
		if (liveQuery == null) {
			listener = new Drools5ResultListener(query);
			liveQuery = driver.getKsession().openLiveQuery(query.toString(), new Object[] {}, listener);
		}
		matches = listener.getMatches();
		return matches;
	}

	@Override
	public void destroy() {
		if (liveQuery != null) {
			liveQuery.close();
		}
	}

}
