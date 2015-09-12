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

import java.io.IOException;

import eu.mondo.sam.core.publishers.CommandLinePublisher;
import eu.mondo.sam.core.results.BenchmarkResult;
import eu.mondo.sam.core.results.CaseDescriptor;

public class TrainBenchmarkCommandLinePublisher extends CommandLinePublisher {

	@Override
	public void publish(BenchmarkResult benchmarkResult) throws IOException {
		CaseDescriptor desc = benchmarkResult.getCaseDescriptor();
		if (desc instanceof TrainBenchmarkCaseDescriptor) {
			TrainBenchmarkCaseDescriptor tbdesc = (TrainBenchmarkCaseDescriptor) desc;
			System.out.println("Running benchmark: " + tbdesc.getModel() + "-"
					+ tbdesc.getSubmodel() + ", " + desc.getScenario() + " scenario, "
					+ desc.getTool() + ", " + desc.getCaseName() + ", " + desc.getSize());
		} else {
			super.publish(benchmarkResult);
		}

	}

}
