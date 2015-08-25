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

package hu.bme.mit.trainbenchmark.benchmark.sesame.matches.schedule;

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleQueryConstans.VAR_COUNT;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleQueryConstans.VAR_STATUS;
import hu.bme.mit.trainbenchmark.benchmark.matches.schedule.ScheduleStatusMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;

import org.openrdf.model.URI;
import org.openrdf.query.BindingSet;

public class SesameScheduleStatusMatch extends SesameMatch implements ScheduleStatusMatch {

	public SesameScheduleStatusMatch(BindingSet bs) {
		super(bs);
	}

	@Override
	public URI[] toArray() {
		return new URI[] { getStatus(), getCount() };
	}

	@Override
	public URI getStatus() {
		System.out.println(bs.getValue(VAR_STATUS).stringValue());
		return (URI) bs.getValue(VAR_STATUS);
	}

	@Override
	public URI getCount() {
		System.out.println(bs.getValue(VAR_COUNT).stringValue());
		return (URI) bs.getValue(VAR_COUNT);
	}

}
