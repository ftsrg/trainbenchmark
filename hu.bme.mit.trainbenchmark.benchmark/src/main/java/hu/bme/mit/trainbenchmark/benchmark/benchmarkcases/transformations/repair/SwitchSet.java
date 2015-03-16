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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;

import java.io.IOException;

public class SwitchSet<T> extends TransformationDefinition<T> {

	@Override
	protected void lhs() throws IOException {
		elementCandidates = currentResults;
	}

	@Override
	protected void rhs() throws IOException {
//		final Map<T, Object> switchPositionProperties = driver.getProperties(elementsToModify, SWITCHPOSITION, SWITCHPOSITION_SWITCH);
//		final Map<T, Object> switchPositionSwitch = driver.getReferences(elementsToModify, SWITCHPOSITION, SWITCH);
//		
//		final Map<Object, Object> newProperties = new HashMap<>(); 
//		
//		for (final Entry<T, Object> entry : switchPositionProperties.entrySet()) {
//			final T switchPosition = entry.getKey();
//			final Object property = entry.getValue();
//			
//			final Object sw = switchPositionSwitch.get(switchPosition);
//			
//			newProperties.put(sw, property);
//		}
//		
//		driver.setProperties(newProperties, SWITCH, SWITCH_CURRENTSTATE);
	}

}
