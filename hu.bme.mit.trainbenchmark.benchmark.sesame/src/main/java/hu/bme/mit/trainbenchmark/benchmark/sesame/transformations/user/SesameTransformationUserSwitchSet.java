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
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.user;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;

import java.util.Collection;

import org.apache.commons.lang.NotImplementedException;
import org.openrdf.model.URI;

public class SesameTransformationUserSwitchSet extends SesameTransformationUser {

	public SesameTransformationUserSwitchSet(final SesameDriver sesameDriver) {
		super(sesameDriver);
	}

	@Override
	public void rhs(final Collection<URI> switches) {
		throw new NotImplementedException();
	}

}
