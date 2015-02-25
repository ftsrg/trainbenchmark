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

package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.benchmarkcases.SignalNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import static hu.bme.mit.trainbenchmark.benchmark.pojo.drools.benchmarkcases.Modifications.modifyModelSignalNeighbor;

public class SignalNeighborUser extends SignalNeighbor implements TransformationBenchmarkCase {

	@Override
	public void modify() {
		modifyModelSignalNeighbor(graph, bmr, Util.calcModify(bmr));
	}
}
