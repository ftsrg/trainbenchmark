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

package hu.bme.mit.trainbenchmark.benchmark.publisher;

import java.io.File;
import java.io.IOException;

import eu.mondo.sam.core.publishers.FilenameFactory;
import eu.mondo.sam.core.publishers.JsonPublisher;
import eu.mondo.sam.core.results.BenchmarkResult;

public class AnalysisJsonPublisher extends JsonPublisher {

	public AnalysisJsonPublisher(FilenameFactory factory) {
		super(factory);
	}

	@Override
	public void publish(BenchmarkResult benchmarkResult) throws IOException {
		File file = new File(getFullname());
		if (!file.exists()) {
			super.publish(benchmarkResult);
		}
	}

}
