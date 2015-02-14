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

package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emf.EMFModification;
import hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

public class RouteSensorUser extends RouteSensor implements TransformationBenchmarkCase {
	@Override
	public void modify() {
		EMFModification.modifyEMFmodelRouteSensor(pack, bmr, Util.calcModify(bmr));
	}

}
