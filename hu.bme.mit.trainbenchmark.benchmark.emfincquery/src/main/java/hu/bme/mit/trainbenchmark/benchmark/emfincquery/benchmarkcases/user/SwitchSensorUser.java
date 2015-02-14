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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emf.EMFModification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

public class SwitchSensorUser extends SwitchSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() {
		EMFModification.modifyEMFmodelSwitchSensor(pack, bmr,
				Util.calcModify(bmr));
	}
}
