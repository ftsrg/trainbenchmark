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
package hu.bme.mit.trainbenchmark.benchmark.viatra.transformations;

import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraBaseDriver;

public abstract class ViatraTransformation<TMatch extends BasePatternMatch>
	extends ModelTransformation<TMatch, ViatraBaseDriver<? extends BasePatternMatch>> {

	public ViatraTransformation(final ViatraBaseDriver<? extends BasePatternMatch> driver) {
		super(driver);
	}

}
