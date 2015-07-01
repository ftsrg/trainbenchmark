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
import hu.bme.mit.trainbenchmark.benchmark.phases.EditPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.InitTransformationPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.InitializationPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.ReadPhase;
import eu.mondo.sam.core.phases.IterationPhase;
import eu.mondo.sam.core.phases.SequencePhase;
import eu.mondo.sam.core.results.CaseDescriptor;

public class InjectScenarioLogic extends
		ScenarioLogic<AbstractBenchmarkCase<?, ?>> {

	@Override
	public void build() {
		IterationPhase iter = new IterationPhase(
				benchmarkConfig.getIterationCount());
		SequencePhase seq = new SequencePhase();
		SequencePhase innerSeq = new SequencePhase();

		CheckPhase check = new CheckPhase("Check");
		EditPhase edit = new EditPhase("Edit");

		innerSeq.addPhases(edit, check);
		iter.setPhase(innerSeq);
		seq.addPhases(new InitializationPhase("Init"),
				new InitTransformationPhase(
						"InitTransformation"),
				new ReadPhase("Read"), check, iter,
				new DestroyPhase("Destroy"));
		rootPhase = seq;

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
}
