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

import hu.bme.mit.trainbenchmark.benchmark.token.TrainBenchmarkDataToken;

import java.io.IOException;

import eu.mondo.sam.core.DataToken;
import eu.mondo.sam.core.phases.AtomicPhase;
import eu.mondo.sam.core.results.PhaseResult;

public class EditPhase extends AtomicPhase {

	public EditPhase(String phaseName) {
		super(phaseName);
	}

	@Override
	public void execute(DataToken token, PhaseResult phaseResult) {
		TrainBenchmarkDataToken trainToken = ((TrainBenchmarkDataToken) token);
		try {
			trainToken.getBenchmarkCase().benchmarkModify(
					phaseResult);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

}
