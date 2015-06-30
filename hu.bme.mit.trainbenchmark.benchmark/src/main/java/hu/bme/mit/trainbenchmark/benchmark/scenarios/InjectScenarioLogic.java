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
import eu.mondo.sam.core.results.CaseDescriptor;
import eu.mondo.sam.core.scenarios.BenchmarkScenario;

public class InjectScenarioLogic extends BenchmarkScenario implements
		ScenarioLogic<AbstractBenchmarkCase<?, ?>> {

	@Override
	public void build() {
		// TODO Auto-generated method stub

	}

	@Override
	public CaseDescriptor getCaseDescriptor() {
		CaseDescriptor descriptor = new CaseDescriptor();
		descriptor.setCaseName(caseName);
		descriptor.setTool(tool);
		descriptor.setScenario("Inject");
		descriptor.setSize(size);
		descriptor.setRunIndex(runIndex);
		return descriptor;
	}

	// @Override
	// public BenchmarkResult runBenchmark(final BenchmarkConfig bc, final
	// AbstractBenchmarkCase<?, ?> benchmarkCase) throws IOException {
	// benchmarkCase.benchmarkInit(bc);
	// benchmarkCase.benchmarkInitTransformation();
	//
	// benchmarkCase.benchmarkRead();
	// benchmarkCase.benchmarkCheck();
	// for (int i = 0; i < bc.getIterationCount(); i++) {
	// benchmarkCase.benchmarkModify();
	// benchmarkCase.benchmarkCheck();
	// }
	// benchmarkCase.benchmarkDestroy();
	//
	// final BenchmarkResult br = benchmarkCase.getBenchmarkResult();
	// br.publish(true);
	// return br;
	// }
}
