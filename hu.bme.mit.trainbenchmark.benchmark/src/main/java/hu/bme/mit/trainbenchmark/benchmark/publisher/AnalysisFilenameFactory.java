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

import eu.mondo.sam.core.publishers.FilenameFactory;

public class AnalysisFilenameFactory implements FilenameFactory {

	protected TrainBenchmarkCaseDescriptor descriptor;

	public AnalysisFilenameFactory(TrainBenchmarkCaseDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	@Override
	public String getFilename() {
		return descriptor.getTool() + "-" + descriptor.getModel() + "-" + descriptor.getScenario()
				+ "-" + descriptor.getSize();
	}

}
