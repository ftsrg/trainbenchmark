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

package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.benchmarkcases.PosLength;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.IOException;

import static hu.bme.mit.trainbenchmark.benchmark.pojo.drools.benchmarkcases.Modifications.modifyModelPosLengthRepair;

public class PosLengthXForm extends PosLength implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		modifyModelPosLengthRepair(bmr, Util.calcModify(bmr), invalids);
	}
}
