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
package hu.bme.mit.trainbenchmark.benchmark.operations;

import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;

public abstract class ModelTransformation<PatternMatch, TDriver extends Driver<?>> {

	protected TDriver driver;
	
	public ModelTransformation(final TDriver driver) {
		this.driver = driver;
	}

	public void activateTransformation(final Collection<PatternMatch> matches) throws Exception {
		driver.beginTransaction();
		activate(matches);
		driver.finishTransaction();
	}
	
	// As the transformations are implemented on a wide range of technologies, they may throw any exception.
	// Using "throws Exception" is generally considered bad practice in production systems.
	// However, it allows us to throw all exceptions similar to unchecked exceptions,
	// hence we found it acceptible in benchmark code.
	public abstract void activate(Collection<PatternMatch> matches) throws Exception;

}
