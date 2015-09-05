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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.phases.CheckPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.DestroyPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.InitTransformationPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.InitializationPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.ReadPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.TransformationPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.analysis.AnalyzerInitializationPhase;
import eu.mondo.sam.core.phases.SequencePhase;

public class RepairScenario extends Scenario<BenchmarkCase<?, ?, ?>> {

	@Override
	public void build() {
		SequencePhase seq = new SequencePhase();
		CheckPhase check = new CheckPhase("Check");
		CheckPhase recheck = new CheckPhase("Recheck");

		// @formatter:off
		seq.addPhases(new InitializationPhase("Init"), 
				new InitTransformationPhase(
				"InitTransformation"), 
				new AnalyzerInitializationPhase("AnalyzerInit"),
				new ReadPhase("Read"), 
				check,
				new TransformationPhase("Edit"), 
				recheck, 
				new DestroyPhase("Destroy")
		);
		// @formatter:on
		rootPhase = seq;

	}

	@Override
	public String getName() {
		return "Repair";
	}

}
