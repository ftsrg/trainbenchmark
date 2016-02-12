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

package hu.bme.mit.trainbenchmark.benchmark.util;

import java.io.IOException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;

public class Util {

	public static long calcNumberOfObjectsToModify(final BenchmarkConfig benchmarkConfig, final int resultSize) {
		final long transformationConstant = benchmarkConfig.getTransformationConstant();

		switch (benchmarkConfig.getTransformationStrategy()) {
		case FIXED:
			return transformationConstant;
		case PROPORTIONAL:
			return resultSize / transformationConstant;
		default:
			throw new UnsupportedOperationException(
					"Transformation strategy " + benchmarkConfig.getTransformationStrategy() + " not supported.");
		}
	}

	public static void executeCommand(final String commandStart, final String exceptionMessage) throws InterruptedException, IOException {
		final Runtime rt = Runtime.getRuntime();
		final Process pr = rt.exec(commandStart);
		pr.waitFor();
		if (pr.exitValue() != 0) {
			throw new IOException("Error code: " + pr.exitValue() + ", " + exceptionMessage);
		}
	}

}
