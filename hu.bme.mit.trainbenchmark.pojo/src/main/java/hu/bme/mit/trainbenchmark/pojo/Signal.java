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

public class Signal extends ListenableObject {

	private static final SignalStateKind SIGNAL_ACTUAL_STATE_EDEFAULT = SignalStateKind.SIGNALSTATEKIND_STOP;

	private SignalStateKind actualState = SIGNAL_ACTUAL_STATE_EDEFAULT;

	public SignalStateKind getActualState() {
		return actualState;
	}

	public void setActualState(SignalStateKind actualState) {
		this.actualState = actualState;
	}
}
