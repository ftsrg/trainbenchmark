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

package hu.bme.mit.trainbenchmark.benchmark.phases;

import java.io.IOException;

import eu.mondo.sam.core.DataToken;
import eu.mondo.sam.core.phases.AtomicPhase;
import eu.mondo.sam.core.results.PhaseResult;
import hu.bme.mit.trainbenchmark.benchmark.token.TrainBenchmarkDataToken;

public class InitTransformationPhase extends AtomicPhase {

	public InitTransformationPhase(final String phaseName) {
		super(phaseName);
	}

	@Override
	public void execute(final DataToken token, final PhaseResult phaseResult) throws IOException {
		final TrainBenchmarkDataToken trainToken = (TrainBenchmarkDataToken) token;
		trainToken.getBenchmarkRunner().initializeTransformation();
	}

}
