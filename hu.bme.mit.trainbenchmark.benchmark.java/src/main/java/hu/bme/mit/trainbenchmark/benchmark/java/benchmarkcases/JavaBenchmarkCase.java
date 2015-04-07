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

package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public abstract class JavaBenchmarkCase<M extends JavaMatch> extends AbstractBenchmarkCase<M, RailwayElement> {

	protected RailwayContainer container;

	@Override
	public void read() {
		final String modelPath = bc.getModelPathNameWithoutExtension() + ".emf";
		final JavaDriver<M> javaDriver = new JavaDriver<>(modelPath);
		driver = javaDriver;

		container = javaDriver.getContainer();
	}

}
