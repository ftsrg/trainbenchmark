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

import org.apache.commons.lang.enums.ValuedEnum;

public class QuerySubtypes extends ValuedEnum {

	private static final long serialVersionUID = 1012887519117305924L;

	public static final QuerySubtypes QUERY_A = new QuerySubtypes("A", 0);
	public static final QuerySubtypes QUERY_B = new QuerySubtypes("B", 1);
	public static final QuerySubtypes QUERY_C = new QuerySubtypes("C", 2);
	public static final QuerySubtypes QUERY_D = new QuerySubtypes("D", 3);
	public static final QuerySubtypes QUERY_E = new QuerySubtypes("E", 4);

	protected QuerySubtypes(String name, int value) {
		super(name, value);
	}

}
