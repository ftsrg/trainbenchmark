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

import org.openrdf.query.BindingSet;

public class SesameScheduleNavigationsMatch extends SesameScheduleCountMatchProcessor {

	public SesameScheduleNavigationsMatch(BindingSet bs) {
		super(bs);
	}

	@Override
	protected String getProcessedName() {
		return "Stations";
	}

}
