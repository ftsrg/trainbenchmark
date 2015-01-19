/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.pojo;

import java.util.List;

public class Switch extends TrackElement {

	private static final SwitchStateKind SWITCH_ACTUAL_STATE_EDEFAULT = SwitchStateKind.POINT_STATE_KIND_FAILURE;

	private SwitchStateKind actualState = SWITCH_ACTUAL_STATE_EDEFAULT;

	private List<SwitchPosition> switchPositions;

	public SwitchStateKind getActualState() {
		return actualState;
	}

	public void setActualState(SwitchStateKind actualState) {
		this.actualState = actualState;
	}

	public List<SwitchPosition> getSwitchPositions() {
		return switchPositions;
	}

	public void setSwitchPositions(List<SwitchPosition> switchPositions) {
		this.switchPositions = switchPositions;
	}
}
