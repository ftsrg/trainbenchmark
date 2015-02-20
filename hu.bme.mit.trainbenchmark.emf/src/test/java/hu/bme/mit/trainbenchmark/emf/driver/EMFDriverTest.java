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
package hu.bme.mit.trainbenchmark.emf.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriverTest;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EMFDriverTest extends DatabaseDriverTest {

	@Override
	public void init() throws IOException {
		final String modelPath = "../models/railway-test-1.concept";

		driver = new EMFDriver(modelPath);
	}

	@Override
	protected long extractLength(final Object segment) throws IOException {	
		final EObject segmentObject = (EObject) segment;
		final EStructuralFeature feature = segmentObject.eClass().getEStructuralFeature(ModelConstants.SEGMENT_LENGTH);
		
		final Integer length = (Integer) segmentObject.eGet(feature);
		return length;
	}

}
