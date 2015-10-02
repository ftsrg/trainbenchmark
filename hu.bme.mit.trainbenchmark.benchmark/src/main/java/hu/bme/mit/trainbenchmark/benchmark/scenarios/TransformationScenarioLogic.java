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

package hu.bme.mit.trainbenchmark.benchmark.scenarios;

import eu.mondo.sam.core.phases.IterationPhase;
import eu.mondo.sam.core.phases.SequencePhase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCaseRunner;
import hu.bme.mit.trainbenchmark.benchmark.phases.CheckPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.DestroyPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.InitTransformationPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.InitializationPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.ReadPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.TransformationPhase;

public class TransformationScenarioLogic extends ScenarioLogic<AbstractBenchmarkCaseRunner<?, ?, ?, ?>> {

	@Override
	public void build() {
		final IterationPhase iteration = new IterationPhase(benchmarkConfig.getIterationCount());
		final SequencePhase sequence = new SequencePhase();
		final SequencePhase iterationSequence = new SequencePhase();

		final TransformationPhase transformation = new TransformationPhase("Transformation");
		final CheckPhase recheck = new CheckPhase("Recheck");

		iterationSequence.addPhases(transformation, recheck);
		iteration.setPhase(iterationSequence);
		sequence.addPhases(new InitializationPhase("Init"), //
				new InitTransformationPhase("InitTransformation"), //
				new ReadPhase("Read"), //
				new CheckPhase("Check"), //
				iteration, //
				new DestroyPhase("Destroy"));
		rootPhase = sequence;
	}

}
