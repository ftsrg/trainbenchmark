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

package hu.bme.mit.trainbenchmark.constants;

public enum ModelType {
	RAILWAY("Railway"), //
	SCHEDULE_RANDOM("Schedule-Random"), //
	SCHEDULE_REAL("Schedule-Real"), //
	SCHEDULE_SCALE_FREE("Schedule-Scale-Free"), //
	SCHEDULE_SCALE_FREE_CHAR("Schedule-Scale-Free-Char"), //
	SCHEDULE_SCALE_FREE_HET("Schedule-Scale-Free-Het"), //
	SCHEDULE_SCALE_FREE_HOM("Schedule-Scale-Free-Hom"), //
	SCHEDULE_WATTS_STROGATZ("Schedule-Watts-Strogatz");

	private String type;

	ModelType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
