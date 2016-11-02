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
package hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations;

import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;

public abstract class IngraphTransformation<TMatch extends IngraphMatch> extends ModelTransformation<TMatch, IngraphDriver> {

	protected IngraphTransformation(final IngraphDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
