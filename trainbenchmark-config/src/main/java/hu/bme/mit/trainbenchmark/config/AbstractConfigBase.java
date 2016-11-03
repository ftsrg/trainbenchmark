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

	protected final Integer initialMemory;
	protected final Integer maxMemory;
	
	public AbstractConfigBase(final Integer initialMemory, final Integer maxMemory) {
		this.initialMemory = initialMemory;
		this.maxMemory = maxMemory;
	}

	public String getWorkspaceDir() {
		return "../";
	}

	public String getModelDir() {
		return getWorkspaceDir() + "models/";
	}

	/**
	 * 
	 * @return The initial memory for the benchmark JVM in MBs.
	 */
	public Integer getInitialMemory() {
		return initialMemory;
	}
	
	/**
	 * 
	 * @return The maximum memory for the benchmark JVM in MBs.
	 */
	public Integer getMaxMemory() {
		return maxMemory;
	}

	/**
	 * 
	 * @return The string required to parameterize the JVM's -Xms option, e.g. "12800M"
	 */
	public String getXms() {
		return initialMemory + "M";
	}

	/**
	 * 
	 * @return The string required to parameterize the JVM's -Xmx option, e.g. "12800M"
	 */
	public String getXmx() {
		return maxMemory + "M";
	}

}
