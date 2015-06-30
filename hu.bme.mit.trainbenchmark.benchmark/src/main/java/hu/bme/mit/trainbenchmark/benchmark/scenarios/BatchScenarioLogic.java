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

package hu.bme.mit.trainbenchmark.benchmark.scenarios;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.phases.CheckPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.DestroyPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.InitializationPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.ReadPhase;
import eu.mondo.sam.core.phases.SequencePhase;
import eu.mondo.sam.core.results.CaseDescriptor;
import eu.mondo.sam.core.scenarios.BenchmarkScenario;

public class BatchScenarioLogic extends BenchmarkScenario implements
	ScenarioLogic<AbstractBenchmarkCase<?, ?>> {

    // @Override
    // public BenchmarkResult runBenchmark(final BenchmarkConfig bc,
    // final AbstractBenchmarkCase<?, ?> benchmarkCase) throws IOException {
    // // benchmarkCase.benchmarkInit(bc);
    //
    // benchmarkCase.benchmarkRead();
    // benchmarkCase.benchmarkCheck();
    // benchmarkCase.benchmarkDestroy();
    //
    // final BenchmarkResult br = benchmarkCase.getBenchmarkResult();
    // br.publish(true);
    // return br;
    // }

    @Override
    public void build() {
	SequencePhase seq = new SequencePhase();
	seq.addPhases(new InitializationPhase("Init"), new ReadPhase("Read"),
		new CheckPhase("Check"), new DestroyPhase("Destroy"));
	rootPhase = seq;

    }

    @Override
    public CaseDescriptor getCaseDescriptor() {
	CaseDescriptor descriptor = new CaseDescriptor();
	descriptor.setCaseName(caseName);
	descriptor.setTool(tool);
	descriptor.setScenario("Batch");
	descriptor.setSize(size);
	descriptor.setRunIndex(runIndex);
	return descriptor;
    }

}
