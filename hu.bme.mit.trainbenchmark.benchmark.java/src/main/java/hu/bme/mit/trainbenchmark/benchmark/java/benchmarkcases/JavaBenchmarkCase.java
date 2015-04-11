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
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaPosLengthMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public class JavaBenchmarkCase<M extends JavaPosLengthMatch> extends AbstractBenchmarkCase<JavaPosLengthMatch, RailwayElement> {

	public JavaBenchmarkCase() {
		driver = new JavaDriverPosLength();
	}

}
