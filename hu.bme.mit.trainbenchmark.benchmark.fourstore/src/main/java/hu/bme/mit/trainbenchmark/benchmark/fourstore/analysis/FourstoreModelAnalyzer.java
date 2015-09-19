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

package hu.bme.mit.trainbenchmark.benchmark.fourstore.analysis;

import hu.bme.mit.trainbenchmark.benchmark.fourstore.driver.FourStoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf.analyzer.RDFModelAnalyzer;

public class FourstoreModelAnalyzer extends RDFModelAnalyzer<FourStoreDriver> {

	public FourstoreModelAnalyzer(FourStoreDriver driver) {
		super(driver);
	}

	@Override
	protected void calculateMetrics() {

	}

}
