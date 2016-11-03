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

package hu.bme.mit.trainbenchmark.config;

public abstract class AbstractConfigBase {

	protected final String xms;
	protected final String xmx;

	public AbstractConfigBase(final String xms, final String xmx) {
		this.xms = xms;
		this.xmx = xmx;
	}

	public String getWorkspaceDir() {
		return "../";
	}
	
	public String getModelDir() {
		return getWorkspaceDir() + "models/";
	}

	public String getXms() {
		return xms;
	}
	
	public String getXmx() {
		return xmx;
	}

}
