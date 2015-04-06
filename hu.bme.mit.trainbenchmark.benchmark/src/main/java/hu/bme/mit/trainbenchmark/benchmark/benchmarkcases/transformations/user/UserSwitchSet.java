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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;

public class UserSwitchSet<T> extends TransformationDefinition<T> {

	@Override
	protected void lhs() throws IOException {
		elementCandidates = driver.collectVertices(ModelConstants.SWITCH);
	}

	@Override
	protected void rhs() throws IOException {
//		final List<Entry<Switch, SwitchStateKind>> itemsToModify = new ArrayList<Map.Entry<Switch,SwitchStateKind>>();
//		for () {
//			final Switch aswitch = switches.get(rndTarget);
//
//			final int oldValue = aswitch.getSwitch_actualState().getValue();
//			final int newValue = (oldValue+1) % 4;
//			final SwitchStateKind newSSK = SwitchStateKind.get(newValue);
//			itemsToModify.add(new AbstractMap.SimpleEntry<Switch, SwitchStateKind>(aswitch, newSSK));
//		}
//		
//		for (final Entry<Switch, SwitchStateKind> switchNewSSKPair : itemsToModify) {
//			final Switch aswitch = switchNewSSKPair.getKey();
//			final SwitchStateKind newSSK = switchNewSSKPair.getValue();
//			aswitch.setSwitch_actualState(newSSK);
//		}
	}

}
